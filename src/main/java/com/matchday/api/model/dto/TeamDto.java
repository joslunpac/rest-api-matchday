package com.matchday.api.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamDto extends BaseDto {

	@NotBlank(message = "The acronym is required")
	@Size(min = 1, max = 4, message = "The acronym must be between 1 and 4 characters")
	private String acronym;

	@NotBlank(message = "The name is required")
	@Size(min = 1, max = 100, message = "The name must be between 1 and 100 characters")
	private String name;

	@Size(min = 1, max = 50, message = "The short name must be between 1 and 50 characters")
	private String shortname;

	@NotBlank(message = "The description is required")
	@Size(min = 1, max = 256, message = "The description must be between 1 and 256 characters")
	private String description;

	@Size(min = 6, max = 6, message = "The primaryColor must be 6 characters")
	private String primaryColor;

	@Size(min = 6, max = 6, message = "The secondaryColor must be 6 characters")
	private String secondaryColor;

	@Size(min = 6, max = 6, message = "The tertiaryColor must be 6 characters")
	private String tertiaryColor;

	@NotNull(message = "The city is required")
	private CityDto city;

	@NotNull(message = "The sport is required")
	private SportDto sport;

	@NotNull(message = "The primaryStadium is required")
	private StadiumDto primaryStadium;

	private StadiumDto secondaryStadium;

}
