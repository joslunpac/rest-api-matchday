package com.matchday.api.model.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StadiumDto extends BaseDto {

	@NotBlank(message = "The name is required")
	@Size(min = 1, max = 100, message = "The name must be between 1 and 100 characters")
	private String name;

	@Size(min = 1, max = 500, message = "The address must be between 1 and 500 characters")
	private String address;

	@NotNull(message = "The city is required")
	private CityDto city;

	private List<CompetitionDto> competitions;

}
