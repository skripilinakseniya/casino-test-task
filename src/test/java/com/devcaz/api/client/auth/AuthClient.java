package com.devcaz.api.client.auth;

import com.devcaz.api.client.auth.model.request.GuestAuthRequest;
import com.devcaz.api.client.auth.model.request.PlayerAuthRequest;
import com.devcaz.PropertiesHolder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;

import static io.restassured.RestAssured.given;

@RequiredArgsConstructor
public class AuthClient {
	public static final String USERNAME_PROPERTY_NAME = "auth.basic.username";
	private final PropertiesHolder propertiesHolder;
	private final RequestSpecification requestSpecification;

	public Response authPlayer(String username, String password) {
		String httpBasicUsername = propertiesHolder.getProperty(USERNAME_PROPERTY_NAME);

		PlayerAuthRequest requestBody = new PlayerAuthRequest("password", username,password);
		return given()
				.auth()
				.preemptive()
				.basic(httpBasicUsername, "")
				.spec(requestSpecification)
				.body(requestBody)
				.when()
				.post("/v2/oauth2/token");

	}

	public Response authGuest() {
		String httpBasicUsername = propertiesHolder.getProperty(USERNAME_PROPERTY_NAME);

		GuestAuthRequest requestBody = new GuestAuthRequest("client_credentials", "guest:default");
		return given()
				.auth()
				.preemptive()
				.basic(httpBasicUsername, "")
				.spec(requestSpecification)
				.body(requestBody)
				.when()
				.post("/v2/oauth2/token");
	}
}
