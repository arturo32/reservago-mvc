package br.ufrn.imd.reservagomvc.checkout.controller;

import br.ufrn.imd.reservagomvc.checkout.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clear")
public class ClearController {
	private final CheckoutService checkoutService;

	@Autowired
	public ClearController(CheckoutService checkoutService) {
		this.checkoutService = checkoutService;
	}

	@DeleteMapping
	public void clear(){
		this.checkoutService.deleteAll();
	}
}
