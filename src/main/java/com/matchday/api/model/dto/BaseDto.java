package com.matchday.api.model.dto;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonView(Views.Public.class)
public class BaseDto implements Serializable {

	private Long id;

}
