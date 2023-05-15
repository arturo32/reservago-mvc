package br.ufrn.imd.reservagomvc.payment.model.dto;

import br.ufrn.imd.reservagomvc.payment.model.CreditCard;
import br.ufrn.imd.reservagomvc.payment.model.Payment;

import java.util.Date;

public record PaymentDto(Long id, CreditCardDto creditCard, Date paymentDate) {
    public PaymentDto(Payment payment) {
        this(payment.getId(), new CreditCardDto(payment.getCreditCard()), payment.getPaymentDate());
    }

}
