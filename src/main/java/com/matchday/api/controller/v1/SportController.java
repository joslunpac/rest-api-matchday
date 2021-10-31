package com.matchday.api.controller.v1;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonView;
import com.matchday.api.exception.ApiNotFoundException;
import com.matchday.api.model.dto.SportDto;
import com.matchday.api.model.dto.Views;
import com.matchday.api.model.entity.Sport;
import com.matchday.api.service.SportService;
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
@Tag(name = SportController.ENTITIES, description = Constants.OA_ENDPOINT + SportController.ENTITIES)
public class SportController {

	public static final String ENTITY = Constants.SPORT;
	public static final String ENTITIES = Constants.SPORTS;

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
	private SportService sportService;

	@Operation(summary = OA_GET_ALL)
	@JsonView(Views.Public.class)
	@GetMapping(path = Constants.V1 + "/sports")
	public ResponseEntity<List<SportDto>> findAll(@RequestParam(defaultValue = "name,asc") String[] sort) {
		List<Sport> sports = sportService.findAll(Sort.by(utilities.buildOrders(sort)));

		if (sports.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		List<SportDto> sportDtos = sports.stream().map(mappers::convertToDto).collect(Collectors.toList());
		return new ResponseEntity<>(sportDtos, HttpStatus.OK);
	}

	@Operation(summary = OA_GET)
	@JsonView(Views.Public.class)
	@GetMapping(path = Constants.V1 + "/sports/{id}")
	public ResponseEntity<SportDto> find(@PathVariable("id") Long id) {
		Sport sport = sportService.findById(id)
				.orElseThrow(() -> new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND));

		SportDto sportDto = mappers.convertToDto(sport);
		return new ResponseEntity<>(sportDto, HttpStatus.OK);
	}

	@Operation(summary = OA_CREATE)
	@JsonView(Views.Private.class)
	@PostMapping(path = Constants.V1 + "/sports")
	public ResponseEntity<SportDto> create(@Valid @RequestBody SportDto sportDto) {
		sportDto.setId(null);
		Sport sport = mappers.convertToEntity(sportDto);
		sportDto = mappers.convertToDto(sportService.save(sport));
		return new ResponseEntity<>(sportDto, HttpStatus.CREATED);
	}

	@Operation(summary = OA_UPDATE)
	@JsonView(Views.Private.class)
	@PutMapping(path = Constants.V1 + "/sports/{id}")
	public ResponseEntity<SportDto> update(@PathVariable("id") Long id, @Valid @RequestBody SportDto sportDto) {
		if (!sportService.existsById(id))
			throw new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND);

		sportDto.setId(id);
		Sport sport = sportService.save(mappers.convertToEntity(sportDto));
		sportDto = mappers.convertToDto(sport);
		return new ResponseEntity<>(sportDto, HttpStatus.OK);
	}

	@Operation(summary = OA_DELETE)
	@JsonView(Views.Private.class)
	@DeleteMapping(path = Constants.V1 + "/sports/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		if (!sportService.existsById(id))
			throw new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND);

		sportService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
