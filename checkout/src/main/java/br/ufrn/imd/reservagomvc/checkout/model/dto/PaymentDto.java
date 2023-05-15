package br.ufrn.imd.reservagomvc.checkout.model.dto;


import java.util.Date;

public record PaymentDto(Long id, CreditCardDto creditCard, Date paymentDate) {

}
