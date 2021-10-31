package com.matchday.api.controller.v1;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonView;
import com.matchday.api.exception.ApiNotFoundException;
import com.matchday.api.model.dto.CompetitionDto;
import com.matchday.api.model.dto.Views;
import com.matchday.api.model.entity.Competition;
import com.matchday.api.service.CompetitionService;
import com.matchday.api.util.Constants;
import com.matchday.api.util.Utilities;
import com.matchday.api.util.mapper.IMappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = CompetitionController.ENTITIES, description = Constants.OA_ENDPOINT + CompetitionController.ENTITIES)
public class CompetitionController {

	public static final String ENTITY = Constants.COMPETITION;
	public static final String ENTITIES = Constants.COMPETITIONS;

	private static final String OA_GET_ALL = Constants.OA_GET_ALL + ENTITIES;
	private static final String OA_GET = Constants.OA_GET + ENTITY;
	private static final String OA_CREATE = Constants.OA_CREATE + ENTITY;
	private static final String OA_UPDATE = Constants.OA_UPDATE + ENTITY;
	private static final String OA_DELETE = Constants.OA_DELETE + ENTITY;

	@Autowired
	private Utilities utilities;

	@Autowired
	private IMappers mappers;

	@Autowired
	private CompetitionService competitionService;

	@Operation(summary = OA_GET_ALL)
	@JsonView(Views.Public.class)
	@GetMapping(path = Constants.V1 + "/competitions")
	public ResponseEntity<List<CompetitionDto>> findAll(@RequestParam(defaultValue = "name,asc") String[] sort) {
		List<Competition> competitions = competitionService.findAll(Sort.by(utilities.buildOrders(sort)));

		if (competitions.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		List<CompetitionDto> competitionDtos = competitions.stream().map(mappers::convertToDto)
				.collect(Collectors.toList());
		return new ResponseEntity<>(competitionDtos, HttpStatus.OK);
	}

	@Operation(summary = OA_GET)
	@JsonView(Views.Public.class)
	@GetMapping(path = Constants.V1 + "/competitions/{id}")
	public ResponseEntity<CompetitionDto> find(@PathVariable("id") Long id) {
		Competition competition = competitionService.findById(id)
				.orElseThrow(() -> new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND));

		CompetitionDto competitionDto = mappers.convertToDto(competition);
		return new ResponseEntity<>(competitionDto, HttpStatus.OK);
	}

	@Operation(summary = OA_CREATE)
	@JsonView(Views.Private.class)
	@PostMapping(path = Constants.V1 + "/competitions")
	public ResponseEntity<CompetitionDto> create(@Valid @RequestBody CompetitionDto competitionDto) {
		competitionDto.setId(null);
		Competition competition = mappers.convertToEntity(competitionDto);
		competitionDto = mappers.convertToDto(competitionService.save(competition));
		return new ResponseEntity<>(competitionDto, HttpStatus.CREATED);
	}

	@Operation(summary = OA_UPDATE)
	@JsonView(Views.Private.class)
	@PutMapping(path = Constants.V1 + "/competitions/{id}")
	public ResponseEntity<CompetitionDto> update(@PathVariable("id") Long id,
			@Valid @RequestBody CompetitionDto competitionDto) {
		if (!competitionService.existsById(id))
			throw new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND);

		competitionDto.setId(id);
		Competition competition = competitionService.save(mappers.convertToEntity(competitionDto));
		competitionDto = mappers.convertToDto(competition);
		return new ResponseEntity<>(competitionDto, HttpStatus.OK);
	}

	@Operation(summary = OA_DELETE)
	@JsonView(Views.Private.class)
	@DeleteMapping(path = Constants.V1 + "/competitions/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		if (!competitionService.existsById(id))
			throw new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND);

		competitionService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
