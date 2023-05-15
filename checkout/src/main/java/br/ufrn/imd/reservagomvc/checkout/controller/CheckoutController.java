package br.ufrn.imd.reservagomvc.checkout.controller;

import br.ufrn.imd.reservagomvc.checkout.model.Checkout;
import br.ufrn.imd.reservagomvc.checkout.model.dto.CheckoutDto;
import br.ufrn.imd.reservagomvc.checkout.model.dto.PaymentDto;
import br.ufrn.imd.reservagomvc.checkout.model.dto.TransactionDto;
import br.ufrn.imd.reservagomvc.checkout.service.CheckoutService;
import br.ufrn.imd.reservagomvc.controller.GenericController;
import br.ufrn.imd.reservagomvc.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
public class CheckoutController extends GenericController<Checkout, CheckoutDto, Long> {

    private final CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @Override
    protected GenericService<Checkout, CheckoutDto, Long> service() {
        return this.checkoutService;
    }

    @GetMapping({"/verify/{id}"})
    public ResponseEntity<Boolean> checkAvailability(@PathVariable Long id) {
        return ResponseEntity.ok(checkoutService.checkAvailability(id));
    }

    @PostMapping({"/book/{placeId}"})
    public ResponseEntity<TransactionDto> bookLocation(@PathVariable Long placeId, @RequestBody PaymentDto paymentDto) {
        return checkoutService.bookLocation(placeId, paymentDto);
    }
}
