package br.ufrn.imd.reservagomvc.payment.service;

import br.ufrn.imd.reservagomvc.payment.model.Transaction;
import br.ufrn.imd.reservagomvc.payment.model.dto.TransactionDto;
import br.ufrn.imd.reservagomvc.payment.repository.TransactionRepository;
import br.ufrn.imd.reservagomvc.respository.GenericRepository;
import br.ufrn.imd.reservagomvc.service.GenericService;
import br.ufrn.imd.reservagomvc.service.PersistenceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransactionService extends GenericService<Transaction, TransactionDto, Long> {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Override
    public TransactionDto convertToDto(Transaction entity) {
        return new TransactionDto(entity);
    }

    @Override
    public Transaction convertToEntity(TransactionDto transactionDto) {
        Transaction user = new Transaction();
        user.setId(transactionDto.id());
        return user;
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
}
