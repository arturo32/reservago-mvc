package br.ufrn.imd.reservagomvc.checkout.model.dto;


import br.ufrn.imd.reservagomvc.checkout.model.Checkout;

public record PlaceDto(Long id, boolean isAvailable, double stars, double value,
					   String name, String location, String description, Integer daysAvailable,
					   Long hostId) {
	public PlaceDto(Checkout checkout) {
		this(checkout.getId(), checkout.isAvailable(), checkout.getStars(), checkout.getValue(), checkout.getName(),
				checkout.getLocation(), checkout.getDescription(), checkout.getDaysAvailable(),
				checkout.getHost().getId());
	}
}
