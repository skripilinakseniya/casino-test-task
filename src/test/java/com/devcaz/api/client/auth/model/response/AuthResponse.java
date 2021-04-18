package com.devcaz.api.client.auth.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class AuthResponse {
	private final String tokenType;
	private final String expiresIn;
	private final String accessToken;
	private final String refreshToken;
}
