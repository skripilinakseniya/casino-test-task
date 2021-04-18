package com.devcaz.api.client.auth.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerAuthRequest {
	private final String grantType;
	private final String username;
	private final String password;
}
