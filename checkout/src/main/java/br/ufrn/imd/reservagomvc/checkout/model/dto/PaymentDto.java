package br.ufrn.imd.reservagomvc.checkout.model.dto;

import br.ufrn.imd.reservagomvc.payment.model.CreditCard;
import br.ufrn.imd.reservagomvc.payment.model.Payment;

import java.util.Date;

public record PaymentDto(Long id, CreditCard creditCard, Date paymentDate) {
    public PaymentDto(Payment payment) {
        this(payment.getId(), payment.getCreditCard(), payment.getPaymentDate());
    }

}
