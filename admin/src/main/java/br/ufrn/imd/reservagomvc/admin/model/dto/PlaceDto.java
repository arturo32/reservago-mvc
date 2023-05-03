package br.ufrn.imd.reservagomvc.admin.model.dto;


import br.ufrn.imd.reservagomvc.admin.model.Place;

public record PlaceDto(Long id, boolean isAvailable, double stars, double value,
					   String name, String location, String description, Integer daysAvailable,
					   Long hostId) {
	public PlaceDto(Place place) {
		this(place.getId(), place.isAvailable(), place.getStars(), place.getValuePerDay(), place.getName(),
				place.getLocation(), place.getDescription(), place.getDaysAvailable(),
				place.getHost().getId());
	}
}
