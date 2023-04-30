package br.ufrn.imd.reservagomvc.payment.model.dto;

import br.ufrn.imd.reservagomvc.payment.model.Transaction;

public record TransactionDto(Long id) {
	public TransactionDto(Transaction transaction) {
		this(transaction.getId());
	}
}
