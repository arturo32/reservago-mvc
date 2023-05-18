package br.ufrn.imd.reservagomvc.checkout.controller;

import br.ufrn.imd.reservagomvc.checkout.model.dto.BookDto;
import br.ufrn.imd.reservagomvc.checkout.model.dto.CheckoutDto;
import br.ufrn.imd.reservagomvc.checkout.model.dto.PaymentDto;
import br.ufrn.imd.reservagomvc.checkout.model.dto.TransactionDto;
import br.ufrn.imd.reservagomvc.checkout.service.CheckoutService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @GetMapping({"/verify/{id}"})
    public ResponseEntity<CheckoutDto> checkAvailability(@PathVariable Long id) {
        return ResponseEntity.ok(checkoutService.checkAvailability(id));
    }

    @PostMapping({"/book/{placeId}"})
    public ResponseEntity<TransactionDto> bookLocation(@PathVariable Long placeId,
            @RequestBody BookDto bookDto) {
        return checkoutService.bookLocation(placeId, bookDto);
    }
}
