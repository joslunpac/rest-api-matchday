package com.matchday.api.model.dto.security;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDto implements Serializable {

	@NotBlank(message = "The name is required")
	@Size(min = 1, max = 100, message = "The name must be between 1 and 100 characters")
	private String name;

	@NotBlank(message = "The username is required")
	@Size(min = 1, max = 100, message = "The username must be between 1 and 100 characters")
	private String username;

	@NotBlank(message = "The email is required")
	@Size(min = 1, max = 100, message = "The email must be between 1 and 100 characters")
	@Email
	private String email;

	@NotBlank(message = "The password is required")
	@Size(min = 4, max = 15, message = "The password must be between 4 and 15 characters")
	private String password;

	private List<RoleDto> roles;

}
