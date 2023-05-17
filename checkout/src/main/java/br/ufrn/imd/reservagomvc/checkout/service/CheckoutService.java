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
public class CheckoutService extends GenericService<Checkout, CheckoutDto, Long> {

    private final CheckoutRepository checkoutRepository;

    @Value("${admin.server.name}")
    private String ADMIN_SERVER_URL;

    @Value("${payment.server.name}")
    private String PAYMENT_SERVER_URL;

    public CheckoutService(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    @Override
    public CheckoutDto convertToDto(Checkout entity) {
        return new CheckoutDto(entity);
    }

    @Override
    public Checkout convertToEntity(CheckoutDto checkoutDto) {
        Checkout checkout = new Checkout();

        checkout.setId(checkoutDto.id());
        checkout.setExpirationDate(checkoutDto.expirationDate());
        checkout.setUserId(checkoutDto.userId());
        checkout.setPlaceId(checkoutDto.placeId());

        return checkout;
    }

    @Override
    protected void validatePersistenceType(PersistenceType persistenceType, CheckoutDto checkoutDto) {

    }

    @Override
    protected void validate(CheckoutDto checkoutDto) {

    }

    @Override
    protected GenericRepository<Checkout, Long> repository() {
        return this.checkoutRepository;
    }

    public boolean checkAvailability(Long placeId) {
        String getPlaceUri = "http://" + ADMIN_SERVER_URL + "/admin/place/" + placeId;
        RestTemplate rst = new RestTemplate();

        ResponseEntity<PlaceDto> response = rst.getForEntity(getPlaceUri, PlaceDto.class);

        PlaceDto place = response.getBody();

        Integer maxNumberOfGuests = place.maxNumberOfGuests();

        Integer currentGuests = this.checkoutRepository
                .countAllByPlaceIdAndActiveIsTrueAndCreationDateGreaterThan(placeId, LocalDateTime.now());


        // TODO: send custom DTO with the number of current guests
        return !currentGuests.equals(maxNumberOfGuests);
    }

    public ResponseEntity<TransactionDto> bookLocation(Long placeId, PaymentDto paymentDto) {
        String performPaymentUri = "http://" + PAYMENT_SERVER_URL + "/payment/transaction/pay";
        RestTemplate rst = new RestTemplate();

        boolean isPlaceAvailable = this.checkAvailability(placeId);

        if (!isPlaceAvailable) {
            TransactionDto failedTransaction = new TransactionDto(null, false,
                    placeId, paymentDto.creditCard().ownerId());
            return ResponseEntity.status(409).body(failedTransaction);
        }

        ResponseEntity<TransactionDto> response = rst.postForEntity(performPaymentUri, paymentDto,
                TransactionDto.class);
        return response;
    }
}
