package br.ufrn.imd.reservagomvc.checkout.service;

import br.ufrn.imd.reservagomvc.checkout.model.Checkout;
import br.ufrn.imd.reservagomvc.checkout.model.Payment;
import br.ufrn.imd.reservagomvc.checkout.model.Transaction;
import br.ufrn.imd.reservagomvc.checkout.model.dto.CheckoutDto;
import br.ufrn.imd.reservagomvc.checkout.model.dto.PaymentDto;
import br.ufrn.imd.reservagomvc.checkout.model.dto.PlaceDto;
import br.ufrn.imd.reservagomvc.checkout.repository.CheckoutRepository;
import br.ufrn.imd.reservagomvc.respository.GenericRepository;
import br.ufrn.imd.reservagomvc.service.GenericService;
import br.ufrn.imd.reservagomvc.service.PersistenceType;
import org.springframework.beans.factory.annotation.Value;
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
        checkout.setAmountOfDays(checkoutDto.amountOfDays());
        checkout.setTransactionId(checkoutDto.transactionId());

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

    public boolean checkAvailability(Long id) {
        String getPlaceUri = "http://" + ADMIN_SERVER_URL + "/admin/place/" + id;
        RestTemplate rst = new RestTemplate();

        ResponseEntity<PlaceDto> response = rst.getForEntity(getPlaceUri, PlaceDto.class);

        PlaceDto place = response.getBody();

        assert place != null;
        return place.available();
    }

    public ResponseEntity<Transaction> bookLocation(Long placeId, PaymentDto paymentDto) {
        String performPaymentUri = "http://" + PAYMENT_SERVER_URL + "/payment/transaction/pay";
        RestTemplate rst = new RestTemplate();

        boolean isPlaceAvailable = this.checkAvailability(placeId);

        if (!isPlaceAvailable) {
            Transaction failedTransaction = new Transaction(false, placeId, paymentDto.creditCard().getOwnerId());
            return ResponseEntity.ok(failedTransaction);
        }

        ResponseEntity<Transaction> response = rst.postForEntity(performPaymentUri, paymentDto, Transaction.class);
        response.getBody().setPlaceId(placeId);
        return response;
    }
}
