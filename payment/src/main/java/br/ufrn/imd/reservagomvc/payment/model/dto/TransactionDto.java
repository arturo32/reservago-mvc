package br.ufrn.imd.reservagomvc.payment.model.dto;

import br.ufrn.imd.reservagomvc.payment.model.Transaction;

public record TransactionDto(Long id, Boolean transactionOk, Long placeId, Long userId) {
	public TransactionDto(Transaction transaction) {
		this(transaction.getId(), transaction.isTransactionOk(), transaction.getPlaceId(), transaction.getUserId());
	}
}
