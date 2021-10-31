package com.matchday.api.model.dto;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonView(Views.Public.class)
public class ResultDto {

	private long totalItems;

	private int totalPages;

	private int currentPage;

	private boolean isFirst;

	private boolean isLast;

	private boolean hasPrevious;

	private boolean hasNext;

	private Object items;

}
