package br.ufrn.imd.reservagomvc.checkout.model.dto;

import br.ufrn.imd.reservagomvc.checkout.model.User;

public record UserDto(Long id, String name, Integer type) {
	public UserDto(User user) {
		this(user.getId(), user.getName(), user.getType());
	}
}