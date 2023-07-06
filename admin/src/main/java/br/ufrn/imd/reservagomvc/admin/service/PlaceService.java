package br.ufrn.imd.reservagomvc.admin.service;

import br.ufrn.imd.reservagomvc.admin.model.Place;
import br.ufrn.imd.reservagomvc.admin.model.dto.PlaceDto;
import br.ufrn.imd.reservagomvc.admin.repository.PlaceRepository;
import br.ufrn.imd.reservagomvc.exception.EntityNotFoundException;
import br.ufrn.imd.reservagomvc.respository.GenericRepository;
import br.ufrn.imd.reservagomvc.service.GenericService;
import br.ufrn.imd.reservagomvc.service.PersistenceType;
import java.util.Optional;
import org.redisson.api.LocalCachedMapOptions;
import org.redisson.api.LocalCachedMapOptions.ReconnectionStrategy;
import org.redisson.api.LocalCachedMapOptions.SyncStrategy;
import org.redisson.api.RLocalCachedMap;
import org.redisson.api.RedissonClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PlaceService extends GenericService<Place, PlaceDto, Long> {

    private RLocalCachedMap<Long, Place> placeMap;

    private final PlaceRepository placeRepository;

    private final UserService userService;


    @Autowired
    public PlaceService(PlaceRepository placeRepository, UserService userService, RedissonClient cacheser) {
        this.placeRepository = placeRepository;
        this.userService = userService;

        LocalCachedMapOptions<Long, Place> mapOptions = LocalCachedMapOptions.<Long, Place>defaults()
                .syncStrategy(SyncStrategy.UPDATE) // If data changes, redis update the local cache of others services
                .reconnectionStrategy(ReconnectionStrategy.CLEAR); // If connection fails, local cache is cleaned after reconnection
        this.placeMap = cacheser.getLocalCachedMap("/place/", new TypedJsonJacksonCodec(String.class, Long.class), mapOptions);
    }

    @Override
    public Place findById(Long id) {
        if(this.placeMap.containsKey(id)){
            this.placeMap.get(id);
        }

        Optional<Place> optionalPlace = this.repository().findByIdAndActiveIsTrue(id);
        if (optionalPlace.isEmpty()) {
            throw new EntityNotFoundException("Entity of id " + String.valueOf(id) + " not found.");
        }

        this.placeMap.fastPut(id, optionalPlace.get());
        return optionalPlace.get();
    }


    @Override
    public PlaceDto convertToDto(Place entity) {
        return new PlaceDto(entity);
    }

    @Override
    public Place convertToEntity(PlaceDto placeDto) {
        Place place = new Place();
        place.setId(placeDto.id());
        place.setAvailable(placeDto.available());
        place.setStars(placeDto.stars());
        place.setValuePerDay(placeDto.valuePerDay());
        place.setName(placeDto.name());
        place.setLocation(placeDto.location());
        place.setDescription(placeDto.description());
        place.setDaysAvailable(placeDto.daysAvailable());
        place.setHost(userService.findById(placeDto.hostId()));
        place.setMaxNumberOfGuests(placeDto.maxNumberOfGuests());
        return place;
    }

    @Override
    protected void validatePersistenceType(PersistenceType persistenceType, PlaceDto placeDto) {

    }

    @Override
    protected void validate(PlaceDto placeDto) {

    }

    @Override
    protected GenericRepository<Place, Long> repository() {
        return this.placeRepository;
    }
}
