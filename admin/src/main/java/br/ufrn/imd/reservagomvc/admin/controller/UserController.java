package br.ufrn.imd.reservagomvc.admin.controller;

import br.ufrn.imd.reservagomvc.admin.model.User;
import br.ufrn.imd.reservagomvc.admin.model.dto.UserDto;
import br.ufrn.imd.reservagomvc.admin.service.UserService;
import br.ufrn.imd.reservagomvc.controller.GenericController;
import br.ufrn.imd.reservagomvc.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController extends GenericController<User, UserDto, Long> {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected GenericService<User, UserDto, Long> service() {
        return this.userService;
    }
}
