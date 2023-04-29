package br.ufrn.imd.reservagomvc.admin.repository;

import br.ufrn.imd.reservagomvc.admin.model.User;
import br.ufrn.imd.reservagomvc.respository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User, Long> {

	@Override
	@Query(value = "DELETE from \"user\" e where e.id = :d_id", nativeQuery = true)
	void trueDeletebyId(@Param(value = "d_id") Long d_id);

	@Override
	@Query(value = "UPDATE \"user\" SET active=false where id = ?1", nativeQuery = true)
	void deleteById(Long arg0);
}
