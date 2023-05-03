package br.ufrn.imd.reservagomvc.admin.model.dto;


import br.ufrn.imd.reservagomvc.admin.model.Place;
import br.ufrn.imd.reservagomvc.admin.model.User;

import java.util.List;

public record PlaceDto(Long id, boolean available, double stars, double valuePerDay,
					   String name, String location, String description, Integer daysAvailable,
					   Long hostId, List<User> guests, Integer maxNumberOfGuests) {
	public PlaceDto(Place place) {
		this(place.getId(), place.isAvailable(), place.getStars(), place.getValuePerDay(), place.getName(),
				place.getLocation(), place.getDescription(), place.getDaysAvailable(),
				place.getHost().getId(), place.getGuests(), place.getMaxNumberOfGuests());
	}
}
