package br.ufrn.imd.reservagomvc.payment.controller;

import br.ufrn.imd.reservagomvc.payment.model.Transaction;
import br.ufrn.imd.reservagomvc.payment.model.dto.PaymentDto;
import br.ufrn.imd.reservagomvc.payment.model.dto.TransactionDto;
import br.ufrn.imd.reservagomvc.payment.service.TransactionService;
import br.ufrn.imd.reservagomvc.controller.GenericController;
import br.ufrn.imd.reservagomvc.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController extends GenericController<Transaction, TransactionDto, Long> {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/pay")
    public ResponseEntity<Transaction> performPayment(PaymentDto paymentDto) {
        return transactionService.performPayment(paymentDto);
    }

    @Override
    protected GenericService<Transaction, TransactionDto, Long> service() {
        return this.transactionService;
    }
}
