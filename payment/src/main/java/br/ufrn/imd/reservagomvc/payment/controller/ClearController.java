package br.ufrn.imd.reservagomvc.payment.controller;

import br.ufrn.imd.reservagomvc.payment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clear")
public class ClearController {
	private final TransactionService transactionService;

	@Autowired
	public ClearController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@DeleteMapping
	public void clear(){
		this.transactionService.deleteAll();
	}
}
