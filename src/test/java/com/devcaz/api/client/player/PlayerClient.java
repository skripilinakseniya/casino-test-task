package com.devcaz.api.client.player;

import com.devcaz.api.client.player.model.RegisterPlayerRequest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;

import static io.restassured.RestAssured.given;

@RequiredArgsConstructor
public class PlayerClient {
	private final RequestSpecification requestSpecification;

	public Response registerPlayer(String guestToken, RegisterPlayerRequest registerPlayerRequest) {
		return given()
				.auth()
				.preemptive()
				.oauth2(guestToken)
				.spec(requestSpecification)
				.body(registerPlayerRequest)
				.when()
				.post("/v2/players");

	}

	public Response fetchPlayer(String playerToken, Long playerId) {
		return given()
				.auth()
				.preemptive()
				.oauth2(playerToken)
				.spec(requestSpecification)
				.pathParam("playerId", playerId)
				.when()
				.get("/v2/players/{playerId}");
	}
}
