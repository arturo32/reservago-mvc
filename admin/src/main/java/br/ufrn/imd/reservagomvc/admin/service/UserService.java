package br.ufrn.imd.reservagomvc.admin.service;

import br.ufrn.imd.reservagomvc.admin.model.User;
import br.ufrn.imd.reservagomvc.admin.model.dto.UserDto;
import br.ufrn.imd.reservagomvc.admin.repository.UserRepository;
import br.ufrn.imd.reservagomvc.respository.GenericRepository;
import br.ufrn.imd.reservagomvc.service.GenericService;
import br.ufrn.imd.reservagomvc.service.PersistenceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends GenericService<User, UserDto, Long> {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override public UserDto converterParaDTO(User entity) {
        return null;
    }

    @Override public User converterParaEntidade(UserDto userDto) {
        return null;
    }

    @Override
    protected void validarModoPersistencia(PersistenceType tipoPersistencia, UserDto userDto) {

    }

    @Override protected void validar(UserDto userDto) {

    }

    @Override protected GenericRepository<User, Long> repositorio() {
        return null;
    }
}
