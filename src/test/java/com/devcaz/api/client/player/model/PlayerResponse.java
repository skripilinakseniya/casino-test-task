package com.devcaz.api.client.player.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerResponse {
	private Long id;
	private String countryId;
	private String timezoneId;
	private String username;
	private String email;
	private String name;
	private String surname;
	private String gender;
	private String phoneNumber;
	private String birthdate;
	private Boolean bonusesAllowed;
	private Boolean isVerified;
}
