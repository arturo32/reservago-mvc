package br.ufrn.imd.reservagomvc.admin.repository;

import br.ufrn.imd.reservagomvc.admin.model.User;
import br.ufrn.imd.reservagomvc.respository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User, Long> {
}
