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


    @Override
    public UserDto convertToDto(User entity) {
        return null;
    }

    @Override
    public User convertToEntity(UserDto userDto) {
        return null;
    }

    @Override
    protected void validatePersistenceType(PersistenceType persistenceType, UserDto userDto) {

    }

    @Override
    protected void validate(UserDto userDto) {

    }

    @Override protected GenericRepository<User, Long> repository() {
        return null;
    }
}
