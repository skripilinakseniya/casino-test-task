package com.devcaz.api.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RestAssuredDefaultConfigurer {
	public static void configureRestAssured() {
		RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
		RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
				(type, s) -> {
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
					objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					return objectMapper;
				}
		));
	}
}
