package br.ufrn.imd.reservagomvc.checkout.service;

import br.ufrn.imd.reservagomvc.checkout.model.Checkout;
import br.ufrn.imd.reservagomvc.checkout.model.dto.BookDto;
import br.ufrn.imd.reservagomvc.checkout.model.dto.CheckoutDto;
import br.ufrn.imd.reservagomvc.checkout.model.dto.PlaceDto;
import br.ufrn.imd.reservagomvc.checkout.model.dto.TransactionDto;
import br.ufrn.imd.reservagomvc.checkout.repository.CheckoutRepository;
import br.ufrn.imd.reservagomvc.exception.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;
import org.redisson.api.LocalCachedMapOptions;
import org.redisson.api.LocalCachedMapOptions.ReconnectionStrategy;
import org.redisson.api.LocalCachedMapOptions.SyncStrategy;
import org.redisson.api.RLocalCachedMap;
import org.redisson.api.RLocalCachedMapReactive;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class CheckoutService {

    private final CheckoutRepository checkoutRepository;

    private RLocalCachedMap<Long, Checkout> checkoutMap;

    private RLocalCachedMap<Long, CheckoutDto> placeMap;
    @Value("${admin.server.name}")
    private String ADMIN_SERVER_URL;

    @Value("${payment.server.name}")
    private String PAYMENT_SERVER_URL;

    @Autowired
    public CheckoutService(CheckoutRepository checkoutRepository, RedissonClient cacheser) {
        this.checkoutRepository = checkoutRepository;
        LocalCachedMapOptions<Long, Checkout> checkoutMapOptions = LocalCachedMapOptions.<Long, Checkout>defaults()
                .syncStrategy(SyncStrategy.UPDATE) // If data changes, redis update the local cache of others services
                .reconnectionStrategy(ReconnectionStrategy.CLEAR); // If connection fails, local cache is cleaned after reconnection
        this.checkoutMap = cacheser.getLocalCachedMap("/checkout/", new TypedJsonJacksonCodec(Long.class, Checkout.class), checkoutMapOptions);

        LocalCachedMapOptions<Long, CheckoutDto> placeMapOptions = LocalCachedMapOptions.<Long, CheckoutDto>defaults()
                .syncStrategy(SyncStrategy.UPDATE) // If data changes, redis update the local cache of others services
                .reconnectionStrategy(ReconnectionStrategy.CLEAR); // If connection fails, local cache is cleaned after reconnection
        this.placeMap = cacheser.getLocalCachedMap("/place-availbility/", new TypedJsonJacksonCodec(Long.class, CheckoutDto.class), placeMapOptions);
    }

    @Autowired
    private RestTemplate rstTemplate;

    public Checkout findById(Long id) {
        if(this.checkoutMap.containsKey(id)){
            return this.checkoutMap.get(id);
        }

        Optional<Checkout> optionalCheckout = checkoutRepository.findById(id);
        if(optionalCheckout.isPresent()) {
            throw new EntityNotFoundException("Entity of id " + String.valueOf(id) + " not found.");
        }

        return this.checkoutRepository.findById(id).get();
    }

    public CheckoutDto checkAvailability(Long placeId) {

        if(this.placeMap.containsKey(placeId)) {
            return this.placeMap.get(placeId);
        }

        String getPlaceUri = "http://" + ADMIN_SERVER_URL + "/admin/place/" + placeId;

        ResponseEntity<PlaceDto> response = this.rstTemplate.getForEntity(getPlaceUri, PlaceDto.class);

        PlaceDto place = response.getBody();

        Integer maxNumberOfGuests = place.maxNumberOfGuests();

        Integer currentGuests = this.checkoutRepository
                .countAllByPlaceIdAndActiveIsTrueAndCheckoutDateGreaterThan(placeId, LocalDateTime.now());

        CheckoutDto checkoutDto = new CheckoutDto(maxNumberOfGuests, currentGuests);
        this.placeMap.fastPut(placeId, checkoutDto);

        return checkoutDto;
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
