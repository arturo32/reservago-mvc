package br.ufrn.imd.reservagomvc.admin.controller;

import br.ufrn.imd.reservagomvc.admin.service.PlaceService;
import br.ufrn.imd.reservagomvc.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clear")
public class ClearController {
	private final PlaceService placeService;
	private final UserService userService;

	@Autowired
	public ClearController(PlaceService placeService, UserService userService) {
		this.placeService = placeService;
		this.userService = userService;
	}

	@DeleteMapping
	public void clear(){
		this.placeService.deleteAll();
		this.userService.deleteAll();
	}
}
