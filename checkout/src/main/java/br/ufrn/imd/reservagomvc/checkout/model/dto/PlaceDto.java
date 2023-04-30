package br.ufrn.imd.reservagomvc.checkout.model.dto;


import br.ufrn.imd.reservagomvc.checkout.model.Place;

public record PlaceDto(Long id, boolean isAvailable, double stars, double value,
					   String name, String location, String description, Integer daysAvailable,
					   Long hostId) {
	public PlaceDto(Place place) {
		this(place.getId(), place.isAvailable(), place.getStars(), place.getValue(), place.getName(),
				place.getLocation(), place.getDescription(), place.getDaysAvailable(),
				place.getHost().getId());
	}
}
