package br.ufrn.imd.reservagomvc.checkout.service;

import br.ufrn.imd.reservagomvc.checkout.model.Checkout;
import br.ufrn.imd.reservagomvc.checkout.model.dto.CheckoutDto;
import br.ufrn.imd.reservagomvc.checkout.model.dto.PaymentDto;
import br.ufrn.imd.reservagomvc.checkout.model.dto.PlaceDto;
import br.ufrn.imd.reservagomvc.checkout.model.dto.TransactionDto;
import br.ufrn.imd.reservagomvc.checkout.repository.CheckoutRepository;
import br.ufrn.imd.reservagomvc.respository.GenericRepository;
import br.ufrn.imd.reservagomvc.service.GenericService;
import br.ufrn.imd.reservagomvc.service.PersistenceType;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
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

    public CheckoutService(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    public CheckoutDto checkAvailability(Long placeId) {
        String getPlaceUri = "http://" + ADMIN_SERVER_URL + "/admin/place/" + placeId;
        RestTemplate rst = new RestTemplate();

        ResponseEntity<PlaceDto> response = rst.getForEntity(getPlaceUri, PlaceDto.class);

        PlaceDto place = response.getBody();

        Integer maxNumberOfGuests = place.maxNumberOfGuests();

        Integer currentGuests = this.checkoutRepository
                .countAllByPlaceIdAndActiveIsTrueAndCreationDateGreaterThan(placeId, LocalDateTime.now());

        return new CheckoutDto(maxNumberOfGuests, currentGuests);
    }

    public ResponseEntity<TransactionDto> bookLocation(Long placeId, PaymentDto paymentDto,
            Date expirationDate) {
        String performPaymentUri = "http://" + PAYMENT_SERVER_URL + "/payment/transaction/pay";
        RestTemplate rst = new RestTemplate();

        boolean isPlaceAvailable = !this.checkAvailability(placeId).isFull();

        if (!isPlaceAvailable) {
            TransactionDto failedTransaction = new TransactionDto(null, false,
                    placeId, paymentDto.creditCard().ownerId());
            return ResponseEntity.status(409).body(failedTransaction);
        }

        Checkout checkout = new Checkout();
        checkout.setPlaceId(placeId);
        checkout.setUserId(paymentDto.creditCard().ownerId());
        checkout.setExpirationDate(expirationDate);
        this.checkoutRepository.save(checkout);

        return rst.postForEntity(performPaymentUri, paymentDto, TransactionDto.class);
    }
}
