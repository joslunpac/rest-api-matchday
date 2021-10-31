package com.matchday.api.model.dto.security;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.matchday.api.model.dto.BaseDto;
import com.matchday.api.model.enumerate.RoleEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleDto extends BaseDto {

	@NotBlank(message = "The name is required")
	@Size(min = 1, max = 10, message = "The name must be between 1 and 10 characters")
	private RoleEnum name;

}
