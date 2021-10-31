package com.matchday.api.controller.v1;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonView;
import com.matchday.api.exception.ApiNotFoundException;
import com.matchday.api.model.dto.StateDto;
import com.matchday.api.model.dto.Views;
import com.matchday.api.model.entity.State;
import com.matchday.api.service.StateService;
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
@Tag(name = StateController.ENTITIES, description = Constants.OA_ENDPOINT + StateController.ENTITIES)
public class StateController {

	public static final String ENTITY = Constants.STATE;
	public static final String ENTITIES = Constants.STATES;

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
	private StateService stateService;

	@Operation(summary = OA_GET_ALL)
	@JsonView(Views.Public.class)
	@GetMapping(path = Constants.V1 + "/states")
	public ResponseEntity<List<StateDto>> findAll(@RequestParam(defaultValue = "name,asc") String[] sort) {
		List<State> states = stateService.findAll(Sort.by(utilities.buildOrders(sort)));

		if (states.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		List<StateDto> stateDtos = states.stream().map(mappers::convertToDto).collect(Collectors.toList());
		return new ResponseEntity<>(stateDtos, HttpStatus.OK);
	}

	@Operation(summary = OA_GET)
	@JsonView(Views.Public.class)
	@GetMapping(path = Constants.V1 + "/states/{id}")
	public ResponseEntity<StateDto> find(@PathVariable("id") Long id) {
		State state = stateService.findById(id)
				.orElseThrow(() -> new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND));

		StateDto stateDto = mappers.convertToDto(state);
		return new ResponseEntity<>(stateDto, HttpStatus.OK);
	}

	@Operation(summary = OA_CREATE)
	@JsonView(Views.Private.class)
	@PostMapping(path = Constants.V1 + "/states")
	public ResponseEntity<StateDto> create(@Valid @RequestBody StateDto stateDto) {
		stateDto.setId(null);
		State state = mappers.convertToEntity(stateDto);
		stateDto = mappers.convertToDto(stateService.save(state));
		return new ResponseEntity<>(stateDto, HttpStatus.CREATED);
	}

	@Operation(summary = OA_UPDATE)
	@JsonView(Views.Private.class)
	@PutMapping(path = Constants.V1 + "/states/{id}")
	public ResponseEntity<StateDto> update(@PathVariable("id") Long id, @Valid @RequestBody StateDto stateDto) {
		if (!stateService.existsById(id))
			throw new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND);

		stateDto.setId(id);
		State state = stateService.save(mappers.convertToEntity(stateDto));
		stateDto = mappers.convertToDto(state);
		return new ResponseEntity<>(stateDto, HttpStatus.OK);
	}

	@Operation(summary = OA_DELETE)
	@JsonView(Views.Private.class)
	@DeleteMapping(path = Constants.V1 + "/states/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		if (!stateService.existsById(id))
			throw new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND);

		stateService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
