package br.ufrn.imd.reservagomvc.payment.model.dto;

import br.ufrn.imd.reservagomvc.payment.model.Transaction;

public record TransactionDto(Long id, Boolean isTransactionOk) {
	public TransactionDto(Transaction transaction) {
		this(transaction.getId(), transaction.getTransactionOk());
	}
}
