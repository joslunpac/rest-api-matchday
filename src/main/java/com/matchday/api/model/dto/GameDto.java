package com.matchday.api.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GameDto extends BaseDto {

	private LocalDate date;

	private LocalTime time;

	@Min(value = 1, message = "The minimum scoreLead is 1")
	private Integer scoreLead;

	@Min(value = 1, message = "The minimum scoreMisses is 1")
	private Integer scoreMisses;

	@NotNull(message = "The competition is required")
	private CompetitionDto competition;

	@NotNull(message = "The localTeam is required")
	private TeamDto localTeam;

	@NotNull(message = "The visitorTeam is required")
	private TeamDto visitorTeam;

	@NotNull(message = "The city is required")
	private CityDto city;

	private StadiumDto assistantStadium;

	private SongDto song;

}
