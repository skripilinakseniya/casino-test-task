package com.devcaz.api;

import com.devcaz.api.client.auth.AuthClient;
import com.devcaz.api.client.auth.model.response.AuthResponse;
import com.devcaz.api.client.player.PlayerClient;
import com.devcaz.api.client.player.model.PlayerResponse;
import com.devcaz.api.client.player.model.RegisterPlayerRequest;
import com.devcaz.PropertiesHolder;
import com.devcaz.api.infra.RestAssuredDefaultConfigurer;
import com.devcaz.api.infra.RestAssuredSpecificationHolder;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.Base64;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CasinoTest {
    private static AuthClient authClient;
    private static PlayerClient playerClient;

    @BeforeAll
    static void prepareData() {
        RestAssuredDefaultConfigurer.configureRestAssured();

        PropertiesHolder propertiesHolder = new PropertiesHolder();
        RestAssuredSpecificationHolder specificationHolder = new RestAssuredSpecificationHolder(propertiesHolder);
        authClient = new AuthClient(propertiesHolder, specificationHolder.getRequestSpecification());
        playerClient = new PlayerClient(specificationHolder.getRequestSpecification());
    }

    @Test
    @DisplayName("Получение токена гостя завершается 200ым статусом. Ответ содержит токен")
    void fetchGuestToken() {
        Response rawResponse = authClient.authGuest();
        AuthResponse guestAuthResponse = rawResponse.as(AuthResponse.class);

        assertEquals(HttpStatus.SC_OK, rawResponse.getStatusCode());
        assertTrue(StringUtils.isNotBlank(guestAuthResponse.getAccessToken()));
    }

    @Test
    @DisplayName("Регистрация пользователя завершается успешно. Ответ содержит необходимые данные пользователя")
    void registerPlayer() {
        AuthResponse guestAuthResponse = authClient.authGuest().as(AuthResponse.class);
        RegisterPlayerRequest registerPlayerRequest = prepareRegisterPlayerRequest();

        Response rawResponse = playerClient.registerPlayer(guestAuthResponse.getAccessToken(), registerPlayerRequest);
        PlayerResponse playerResponse = rawResponse.as(PlayerResponse.class);

        assertEquals(HttpStatus.SC_CREATED, rawResponse.getStatusCode());
        checkNotNullResponse(playerResponse);
        checkNotEmptyResponse(playerResponse);
    }


    @Test
    @DisplayName("Авторизация под созданным пользователем завершается успешно 200ым статусом. Ответ содержит токен")
    void createdPlayerAuth() {
        AuthResponse guestAuthResponse = authClient.authGuest().as(AuthResponse.class);

        RegisterPlayerRequest registerPlayerRequest = prepareRegisterPlayerRequest();
        playerClient.registerPlayer(guestAuthResponse.getAccessToken(), registerPlayerRequest);

        Response playerAuthRawResponse = authClient.authPlayer(registerPlayerRequest.getUsername(), registerPlayerRequest.getPasswordChange());
        AuthResponse playerAuthResponse = playerAuthRawResponse.as(AuthResponse.class);

        assertEquals(HttpStatus.SC_OK, playerAuthRawResponse.getStatusCode());
        assertTrue(StringUtils.isNotBlank(playerAuthResponse.getAccessToken()));
    }

    @Test
    @DisplayName("Запрос данных о профиле игрока успешен. Ответ содержит необходимые данные пользователя")
    void getPlayerData() {
        AuthResponse guestAuthResponse = authClient.authGuest().as(AuthResponse.class);
        RegisterPlayerRequest registerPlayerRequest = prepareRegisterPlayerRequest();

        PlayerResponse registerPlayerResponse = playerClient.registerPlayer(guestAuthResponse.getAccessToken(), registerPlayerRequest)
                .as(PlayerResponse.class);

        AuthResponse playerAuthResponse = authClient.authPlayer(registerPlayerRequest.getUsername(), registerPlayerRequest.getPasswordChange())
                .as(AuthResponse.class);

        Response fetchPlayerRawResponse = playerClient.fetchPlayer(playerAuthResponse.getAccessToken(), registerPlayerResponse.getId());
        PlayerResponse fetchPlayerResponse = fetchPlayerRawResponse.as(PlayerResponse.class);

        assertEquals(HttpStatus.SC_OK, fetchPlayerRawResponse.getStatusCode());
        checkNotNullResponse(fetchPlayerResponse);
        checkNotEmptyResponse(fetchPlayerResponse);
    }

    @Test
    @DisplayName("Запрос данных о профиле другого игрока. Ответ 404")
    void getOtherPlayerData() {
        //зайти под гостем
        AuthResponse guestAuthResponse = authClient.authGuest().as(AuthResponse.class);

        //регистритуем первого игрока
        RegisterPlayerRequest registerPlayerRequest = prepareRegisterPlayerRequest();
        playerClient.registerPlayer(guestAuthResponse.getAccessToken(), registerPlayerRequest);

        //регистритуем второго игрока
        RegisterPlayerRequest registerOtherPlayerRequest = prepareRegisterPlayerRequest();
        Response registerOtherPlayerRawResponse = playerClient.registerPlayer(guestAuthResponse.getAccessToken(), registerOtherPlayerRequest);
        PlayerResponse registerOtherPlayerResponse = registerOtherPlayerRawResponse.as(PlayerResponse.class);

        //входим под первым игроком
        AuthResponse playerAuthResponse = authClient.authPlayer(registerPlayerRequest.getUsername(), registerPlayerRequest.getPasswordChange())
                .as(AuthResponse.class);

        //запрашиваем данные о другом игроке
        Response fetchPlayerRawResponse = playerClient.fetchPlayer(playerAuthResponse.getAccessToken(), registerOtherPlayerResponse.getId());

        assertEquals(HttpStatus.SC_NOT_FOUND, fetchPlayerRawResponse.getStatusCode());
    }

    private RegisterPlayerRequest prepareRegisterPlayerRequest() {
        Faker faker = new Faker();
        Name name = faker.name();
        String firstName = name.firstName();
        String lastName = name.lastName();
        String base64EncodedPassword = new String(Base64.getEncoder().encode(faker.internet().password().getBytes()));
        String username = UUID.randomUUID().toString();
        String email = String.format("%s@example.com", username);

        return new RegisterPlayerRequest(username, base64EncodedPassword, base64EncodedPassword, email, firstName, lastName, "RUB");
    }

    private void checkNotEmptyResponse(PlayerResponse playerResponse) {
        assertTrue(StringUtils.isNotBlank(playerResponse.getUsername()));
        assertTrue(StringUtils.isNotBlank(playerResponse.getEmail()));
        assertTrue(StringUtils.isNotBlank(playerResponse.getName()));
        assertTrue(StringUtils.isNotBlank(playerResponse.getSurname()));
    }

    private void checkNotNullResponse(PlayerResponse playerResponse) {
        assertNotNull(playerResponse.getId());
        assertNotNull(playerResponse.getBonusesAllowed());
        assertNotNull(playerResponse.getIsVerified());
    }
}
