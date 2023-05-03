package br.ufrn.imd.reservagomvc.checkout.model.dto;

import br.ufrn.imd.reservagomvc.checkout.model.Checkout;

public record CheckoutDto(Long id, Integer amountOfDays, Long transactionId) {
    public CheckoutDto(Checkout checkout) {
        this(checkout.getId(), checkout.getAmountOfDays(), checkout.getTransactionId());
    }
}
