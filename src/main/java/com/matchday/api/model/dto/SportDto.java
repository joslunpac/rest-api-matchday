package com.matchday.api.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SportDto extends BaseDto {

	@NotBlank(message = "The name is required")
	@Size(min = 1, max = 100, message = "The name must be between 1 and 100 characters")
	private String name;

}
