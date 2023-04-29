package br.ufrn.imd.reservagomvc.admin.service;

import br.ufrn.imd.reservagomvc.admin.model.Place;
import br.ufrn.imd.reservagomvc.admin.model.dto.PlaceDto;
import br.ufrn.imd.reservagomvc.admin.repository.PlaceRepository;
import br.ufrn.imd.reservagomvc.respository.GenericRepository;
import br.ufrn.imd.reservagomvc.service.GenericService;
import br.ufrn.imd.reservagomvc.service.PersistenceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PlaceService extends GenericService<Place, PlaceDto, Long> {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }


    @Override
    public PlaceDto convertToDto(Place entity) {
        return new PlaceDto(entity);
    }

    @Override
    public Place convertToEntity(PlaceDto placeDto) {
        Place place = new Place();
        place.setId(placeDto.id());
        place.setAvailable(placeDto.isAvailable());
        place.setStars(placeDto.stars());
        place.setValue(placeDto.value());
        place.setName(placeDto.name());
        place.setLocation(placeDto.location());
        place.setDescription(placeDto.description());
        place.setDaysAvailable(placeDto.daysAvailable());
        place.setHostId(placeDto.hostId());
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
