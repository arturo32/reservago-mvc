package br.ufrn.imd.reservagomvc.checkout.repository;

import br.ufrn.imd.reservagomvc.checkout.model.Checkout;
import br.ufrn.imd.reservagomvc.respository.GenericRepository;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepository extends GenericRepository<Checkout, Long> {

	Integer countAllByPlaceIdAndActiveIsTrueAndCreationDateGreaterThan(Long placeId, LocalDateTime currentDate);
}
