package com.devcaz.api.client.auth.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GuestAuthRequest {
	private final String grantType;
	private final String scope;
}
