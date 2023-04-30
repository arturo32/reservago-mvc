package br.ufrn.imd.reservagomvc.payment.repository;

import br.ufrn.imd.reservagomvc.payment.model.Transaction;
import br.ufrn.imd.reservagomvc.respository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends GenericRepository<Transaction, Long> {
}
