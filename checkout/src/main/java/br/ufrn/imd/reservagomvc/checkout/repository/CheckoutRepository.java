package br.ufrn.imd.reservagomvc.checkout.repository;

import br.ufrn.imd.reservagomvc.checkout.model.Checkout;
import br.ufrn.imd.reservagomvc.respository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepository extends GenericRepository<Checkout, Long> {

}
