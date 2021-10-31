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
public class CompetitionDto extends BaseDto {

	@NotBlank(message = "The name is required")
	@Size(min = 1, max = 100, message = "The name must be between 1 and 100 characters")
	private String name;

	@NotBlank(message = "The description is required")
	@Size(min = 1, max = 256, message = "The description must be between 1 and 256 characters")
	private String description;

	private CompetitionDto fatherCompetition;

	@NotNull(message = "The sport is required")
	private SportDto sport;

	private List<StadiumDto> stadiums;

}
