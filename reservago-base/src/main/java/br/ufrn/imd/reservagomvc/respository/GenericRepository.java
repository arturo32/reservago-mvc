package br.ufrn.imd.reservagomvc.respository;

import br.ufrn.imd.reservagomvc.model.GenericModel;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface GenericRepository<T extends GenericModel<PK>, PK extends Serializable> extends JpaRepository<T, PK> {

	List<T> findAllByActiveIsTrueAndIdIsInOrderByCreationDateDesc(List<PK> ids);

	List<T> findAllByActiveIsTrueAndIdBetweenOrderByCreationDateDesc(PK start, PK end);

	List<T> findAllByActiveIsTrueOrderByCreationDateDesc(Pageable pageable);
	
	Optional<T> findByIdAndActiveIsTrue(PK arg0);
	
	Optional<T> findByIdAndActiveIsFalse(PK arg0);

	@Override
	default void delete(T arg0) {
		deleteById(arg0.getId());
	}

	default void trueDelete(T arg0) {
		trueDeletebyId(arg0.getId());
	}

	@Transactional
	@Modifying
	@Query(value = "DELETE from #{#entityName} e where e.id = :d_id", nativeQuery = true)
	void trueDeletebyId(@Param(value = "d_id") PK d_id);

	@Override
	@Transactional
	@Modifying
	@Query(value = "UPDATE #{#entityName} SET active=false where id = ?1", nativeQuery = true)
	void deleteById(PK arg0);

	@Override
	default void deleteAll(Iterable<? extends T> arg0) {
		arg0.forEach(entity -> deleteById(entity.getId()));
	}
}
