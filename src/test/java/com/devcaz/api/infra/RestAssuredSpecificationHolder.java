package com.devcaz.api.infra;

import com.devcaz.PropertiesHolder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

@Getter
public class RestAssuredSpecificationHolder {
	private final RequestSpecification requestSpecification;

	public RestAssuredSpecificationHolder(PropertiesHolder propertiesHolder) {
		requestSpecification = prepareRequestSpecification(propertiesHolder.getProperty("url"));
	}


	private RequestSpecification prepareRequestSpecification(String url) {
		return new RequestSpecBuilder()
				.setBaseUri(url)
				.setAccept(ContentType.JSON)
				.setContentType(ContentType.JSON)
				.build();
	}
}
