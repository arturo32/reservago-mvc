package br.ufrn.imd.reservagomvc.payment.service;

import br.ufrn.imd.reservagomvc.exception.EntityNotFoundException;
import br.ufrn.imd.reservagomvc.payment.model.Transaction;
import br.ufrn.imd.reservagomvc.payment.model.dto.PaymentDto;
import br.ufrn.imd.reservagomvc.payment.model.dto.TransactionDto;
import br.ufrn.imd.reservagomvc.payment.repository.TransactionRepository;
import br.ufrn.imd.reservagomvc.respository.GenericRepository;
import br.ufrn.imd.reservagomvc.service.GenericService;
import br.ufrn.imd.reservagomvc.service.PersistenceType;
import java.util.Optional;
import org.redisson.api.LocalCachedMapOptions;
import org.redisson.api.LocalCachedMapOptions.ReconnectionStrategy;
import org.redisson.api.LocalCachedMapOptions.SyncStrategy;
import org.redisson.api.RLocalCachedMap;
import org.redisson.api.RedissonClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class TransactionService extends GenericService<Transaction, TransactionDto, Long> {

    private final TransactionRepository transactionRepository;

    private RLocalCachedMap<Long, Transaction> transactionMap;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, RedissonClient cacheser) {
        this.transactionRepository = transactionRepository;

        LocalCachedMapOptions<Long, Transaction> mapOptions = LocalCachedMapOptions.<Long, Transaction>defaults()
                .syncStrategy(SyncStrategy.UPDATE) // If data changes, redis update the local cache of others services
                .reconnectionStrategy(ReconnectionStrategy.CLEAR); // If connection fails, local cache is cleaned after reconnection
        this.transactionMap = cacheser.getLocalCachedMap("/transaction/", new TypedJsonJacksonCodec(String.class, Long.class), mapOptions);
    }

    @Override
    public Transaction findById(Long id) {
        if(this.transactionMap.containsKey(id)){
            this.transactionMap.get(id);
        }

        Optional<Transaction> optionalTransaction = this.repository().findByIdAndActiveIsTrue(id);
        if (optionalTransaction.isEmpty()) {
            throw new EntityNotFoundException("Entity of id " + String.valueOf(id) + " not found.");
        }
        this.transactionMap.fastPut(id, optionalTransaction.get());
        return optionalTransaction.get();
    }

    @Override
    public TransactionDto convertToDto(Transaction entity) {
        return new TransactionDto(entity);
    }

    @Override
    public Transaction convertToEntity(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();

        transaction.setId(transactionDto.id());
        transaction.setTransactionOk(transactionDto.transactionOk());
        transaction.setPlaceId(transactionDto.placeId());
        transaction.setUserId(transactionDto.userId());

        return transaction;
    }

    @Override
    protected void validatePersistenceType(PersistenceType persistenceType, TransactionDto transactionDto) {

    }

    @Override
    protected void validate(TransactionDto transactionDto) {

    }

    @Override protected GenericRepository<Transaction, Long> repository() {
        return this.transactionRepository;
    }

    public ResponseEntity<Transaction> performPayment(PaymentDto paymentDto) {
        // Do some validation logic
        Transaction transaction = new Transaction();
        transaction.setUserId(paymentDto.creditCard().ownerId());
        transaction.setTransactionOk(true);
        //transaction.setPlaceId();....

        // Payment will be used, I swear
        return ResponseEntity.ok(this.repository().save(transaction));
    }
}
