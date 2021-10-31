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
@Table(name = "competition")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Competition extends BaseEntity {

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false, length = 256)
	private String description;

	@ManyToOne
	@JoinColumn(name = "competition_id", referencedColumnName = "id")
	private Competition fatherCompetition;

	@ManyToOne
	@JoinColumn(name = "sport_id", referencedColumnName = "id", nullable = false)
	private Sport sport;

}
