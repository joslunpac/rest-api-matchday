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
@Table(name = "state")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class State extends BaseEntity {

	@Column(nullable = false, length = 3)
	private String acronym;

	@Column(nullable = false, length = 100)
	private String name;

	@ManyToOne
	@JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
	private Country country;

}
