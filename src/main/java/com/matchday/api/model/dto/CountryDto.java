package com.matchday.api.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CountryDto extends BaseDto {

	@NotBlank(message = "The acronym is required")
	@Size(min = 1, max = 3, message = "The acronym must be between 1 and 3 characters")
	private String acronym;

	@NotBlank(message = "The name is required")
	@Size(min = 1, max = 100, message = "The name must be between 1 and 100 characters")
	private String name;

}
