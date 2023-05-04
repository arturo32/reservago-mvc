package br.ufrn.imd.reservagomvc.checkout.model.dto;


import java.util.List;

public record PlaceDto(Long id, boolean available, double stars, double valuePerDay,
					   String name, String location, String description, Integer daysAvailable,
					   Long hostId, List<Integer> guests, Integer maxNumberOfGuests) {
}
