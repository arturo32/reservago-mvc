package br.ufrn.imd.reservagomvc.checkout.service;

import br.ufrn.imd.reservagomvc.checkout.model.Checkout;
import br.ufrn.imd.reservagomvc.checkout.model.dto.BookDto;
import br.ufrn.imd.reservagomvc.checkout.model.dto.CheckoutDto;
import br.ufrn.imd.reservagomvc.checkout.model.dto.PlaceDto;
import br.ufrn.imd.reservagomvc.checkout.model.dto.TransactionDto;
import br.ufrn.imd.reservagomvc.checkout.repository.CheckoutRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CheckoutService {

    private final CheckoutRepository checkoutRepository;
    @Value("${admin.server.name}")
    private String ADMIN_SERVER_URL;

    @Value("${payment.server.name}")
    private String PAYMENT_SERVER_URL;

    @Autowired
    public CheckoutService(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    @Autowired
    private RestTemplate rstTemplate;

    public CheckoutDto checkAvailability(Long placeId) {
        String getPlaceUri = "http://" + ADMIN_SERVER_URL + "/admin/place/" + placeId;

        ResponseEntity<PlaceDto> response = this.rstTemplate.getForEntity(getPlaceUri, PlaceDto.class);

        PlaceDto place = response.getBody();

        Integer maxNumberOfGuests = place.maxNumberOfGuests();

        Integer currentGuests = this.checkoutRepository
                .countAllByPlaceIdAndActiveIsTrueAndCheckoutDateGreaterThan(placeId, LocalDateTime.now());

        return new CheckoutDto(maxNumberOfGuests, currentGuests);
    }

    public ResponseEntity<TransactionDto> bookLocation(Long placeId, BookDto bookDto) {
        String performPaymentUri = "http://" + PAYMENT_SERVER_URL + "/payment/transaction/pay";

        boolean isPlaceAvailable = !this.checkAvailability(placeId).isFull();

        if (!isPlaceAvailable) {
            TransactionDto failedTransaction = new TransactionDto(null, false,
                    placeId, bookDto.paymentDto().creditCard().ownerId());
            return ResponseEntity.status(409).body(failedTransaction);
        }

        Checkout checkout = new Checkout();
        checkout.setPlaceId(placeId);
        checkout.setUserId(bookDto.paymentDto().creditCard().ownerId());
        checkout.setCheckoutDate(bookDto.checkoutDate());
        this.checkoutRepository.save(checkout);

        return this.rstTemplate.postForEntity(performPaymentUri, bookDto.paymentDto(), TransactionDto.class);
    }

    public void deleteAll() {
        this.checkoutRepository.deleteAll();
    }
}
