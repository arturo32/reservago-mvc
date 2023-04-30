package br.ufrn.imd.reservagomvc.checkout.service;

import br.ufrn.imd.reservagomvc.checkout.model.User;
import br.ufrn.imd.reservagomvc.checkout.model.dto.UserDto;
import br.ufrn.imd.reservagomvc.checkout.repository.UserRepository;
import br.ufrn.imd.reservagomvc.respository.GenericRepository;
import br.ufrn.imd.reservagomvc.service.GenericService;
import br.ufrn.imd.reservagomvc.service.PersistenceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService extends GenericService<User, UserDto, Long> {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDto convertToDto(User entity) {
        return new UserDto(entity);
    }

    @Override
    public User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.id());
        user.setName(userDto.name());
        user.setType(userDto.type());
        return user;
    }

    @Override
    protected void validatePersistenceType(PersistenceType persistenceType, UserDto userDto) {

    }

    @Override
    protected void validate(UserDto userDto) {

    }

    @Override protected GenericRepository<User, Long> repository() {
        return this.userRepository;
    }
}
