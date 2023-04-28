package br.ufrn.imd.reservagomvc.admin.repository;

import br.ufrn.imd.reservagomvc.admin.model.Place;
import br.ufrn.imd.reservagomvc.respository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends GenericRepository<Place, Long> {
}
