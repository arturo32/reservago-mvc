package br.ufrn.imd.reservagomvc.checkout.service;

import br.ufrn.imd.reservagomvc.checkout.model.Checkout;
import br.ufrn.imd.reservagomvc.checkout.model.dto.PlaceDto;
import br.ufrn.imd.reservagomvc.checkout.repository.PlaceRepository;
import br.ufrn.imd.reservagomvc.respository.GenericRepository;
import br.ufrn.imd.reservagomvc.service.GenericService;
import br.ufrn.imd.reservagomvc.service.PersistenceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class PlaceService extends GenericService<Checkout, PlaceDto, Long> {

    private final PlaceRepository placeRepository;

    private final UserService userService;

    @Autowired
    public PlaceService(PlaceRepository placeRepository, UserService userService) {
        this.placeRepository = placeRepository;
        this.userService = userService;
    }


    @Override
    public PlaceDto convertToDto(Checkout entity) {
        return new PlaceDto(entity);
    }

    @Override
    public Checkout convertToEntity(PlaceDto placeDto) {
        Checkout checkout = new Checkout();
        checkout.setId(placeDto.id());
        checkout.setAvailable(placeDto.isAvailable());
        checkout.setStars(placeDto.stars());
        checkout.setValue(placeDto.value());
        checkout.setName(placeDto.name());
        checkout.setLocation(placeDto.location());
        checkout.setDescription(placeDto.description());
        checkout.setDaysAvailable(placeDto.daysAvailable());
        checkout.setHost(userService.findById(placeDto.hostId()));
        return checkout;
    }

    @Override
    protected void validatePersistenceType(PersistenceType persistenceType, PlaceDto placeDto) {

    }

    @Override
    protected void validate(PlaceDto placeDto) {

    }

    @Override
    protected GenericRepository<Checkout, Long> repository() {
        return this.placeRepository;
    }

	public void checkAvailability(Long id) {
        String BASE_URL = "http://localhost:8100/admin/place/";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PlaceDto> response = restTemplate.getForEntity(BASE_URL + id, PlaceDto.class);
        System.out.println(response.getBody().name());
        System.out.println(response.getBody().isAvailable());
	}
}
