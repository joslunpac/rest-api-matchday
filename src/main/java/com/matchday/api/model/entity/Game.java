package com.matchday.api.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "game")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game extends BaseEntity {

	@Column
	private LocalDate date;

	@Column
	private LocalTime time;

	@Column(name = "score_lead", length = 2)
	private Integer scoreLead;

	@Column(name = "score_misses", length = 2)
	private Integer scoreMisses;

	@ManyToOne
	@JoinColumn(name = "competition_id", referencedColumnName = "id", nullable = false)
	private Competition competition;

	@ManyToOne
	@JoinColumn(name = "team_local_id", referencedColumnName = "id", nullable = false)
	private Team localTeam;

	@ManyToOne
	@JoinColumn(name = "team_visitor_id", referencedColumnName = "id", nullable = false)
	private Team visitorTeam;

	@ManyToOne
	@JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
	private City city;

	@ManyToOne
	@JoinColumn(name = "stadium_id", referencedColumnName = "id")
	private Stadium assistantStadium;

	@ManyToOne
	@JoinColumn(name = "song_id", referencedColumnName = "id")
	private Song song;

}
