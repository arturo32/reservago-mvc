package br.ufrn.imd.reservagomvc.checkout.model.dto;

import java.time.LocalDateTime;

public record BookDto(PaymentDto paymentDto, LocalDateTime checkoutDate) {}
