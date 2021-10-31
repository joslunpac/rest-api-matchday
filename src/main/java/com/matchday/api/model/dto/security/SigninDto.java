package com.matchday.api.model.dto.security;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigninDto implements Serializable {

	@NotNull(message = "The username is required")
	private String username;

	@NotNull(message = "The password is required")
	private String password;

}
