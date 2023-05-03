package br.ufrn.imd.reservagomvc.checkout.controller;

import br.ufrn.imd.reservagomvc.checkout.model.Checkout;
import br.ufrn.imd.reservagomvc.checkout.model.dto.PlaceDto;
import br.ufrn.imd.reservagomvc.checkout.service.PlaceService;
import br.ufrn.imd.reservagomvc.controller.GenericController;
import br.ufrn.imd.reservagomvc.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
public class CheckoutController extends GenericController<Checkout, PlaceDto, Long> {

    private final PlaceService placeService;

    @Autowired
    public CheckoutController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @Override
    protected GenericService<Checkout, PlaceDto, Long> service() {
        return this.placeService;
    }

    @Override
    @GetMapping({"/{id}"})
    public ResponseEntity<PlaceDto> findById(@PathVariable Long id) {
        placeService.checkAvailability(id);

        return ResponseEntity.ok(this.service().convertToDto(this.service().findById(id)));
    }

}
