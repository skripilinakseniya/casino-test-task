package com.devcaz.api.client.player.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterPlayerRequest {
	private final String username;
	private final String passwordChange;
	private final String passwordRepeat;
	private final String email;
	private final String name;
	private final String surname;
	private final String currencyCode;
}
