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
@Table(name = "stadium")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stadium extends BaseEntity {

	@Column(nullable = false, length = 100)
	private String name;

	@Column(length = 500)
	private String address;

	@ManyToOne
	@JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
	private City city;

}
