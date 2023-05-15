package br.ufrn.imd.reservagomvc.checkout.model.dto;

public record TransactionDto(Long id, Boolean transactionOk, Long placeId, Long userId) {}
