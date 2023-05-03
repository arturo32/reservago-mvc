package br.ufrn.imd.reservagomvc.payment.model.dto;

import br.ufrn.imd.reservagomvc.payment.model.CreditCard;

public record CreditCardDto(Long id, Long ownerId, String number, Double balance, Integer verificationNumber) {
    public CreditCardDto(CreditCard creditCard) {
        this(creditCard.getId(), creditCard.getOwnerId(), creditCard.getNumber(), creditCard.getBalance(), creditCard.getVerificationNumber());
    }
}
