package com.matchday.api.model.entity;

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
@Table(name = "team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team extends BaseEntity {

	@Column(nullable = false, length = 4)
	private String acronym;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(name = "short_name", length = 50)
	private String shortname;

	@Column(name = "primary_color", length = 6)
	private String primaryColor;

	@Column(name = "secondary_color", length = 6)
	private String secondaryColor;

	@Column(name = "tertiary_color", length = 6)
	private String tertiaryColor;

	@ManyToOne
	@JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
	private City city;

	@ManyToOne
	@JoinColumn(name = "sport_id", referencedColumnName = "id", nullable = false)
	private Sport sport;

	@ManyToOne
	@JoinColumn(name = "stadium_primary_id", referencedColumnName = "id", nullable = false)
	private Stadium primaryStadium;

	@ManyToOne
	@JoinColumn(name = "stadium_secondary_id", referencedColumnName = "id")
	private Stadium secondaryStadium;

}
