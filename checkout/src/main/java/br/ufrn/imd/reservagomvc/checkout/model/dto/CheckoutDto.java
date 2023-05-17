package br.ufrn.imd.reservagomvc.checkout.model.dto;

import br.ufrn.imd.reservagomvc.checkout.model.Checkout;
import java.util.Date;

public record CheckoutDto(Long id, Date expirationDate, Long userId, Long placeId) {
    public CheckoutDto(Checkout checkout) {
        this(checkout.getId(), checkout.getExpirationDate(), checkout.getUserId(), checkout.getPlaceId());
    }
}
