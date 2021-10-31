package com.matchday.api.controller.v1;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonView;
import com.matchday.api.exception.ApiNotFoundException;
import com.matchday.api.model.dto.StadiumDto;
import com.matchday.api.model.dto.Views;
import com.matchday.api.model.entity.Stadium;
import com.matchday.api.service.StadiumService;
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
@Tag(name = StadiumController.ENTITIES, description = Constants.OA_ENDPOINT + StadiumController.ENTITIES)
public class StadiumController {

	public static final String ENTITY = Constants.STADIUM;
	public static final String ENTITIES = Constants.STADIUMS;

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
	private StadiumService stadiumService;

	@Operation(summary = OA_GET_ALL)
	@JsonView(Views.Public.class)
	@GetMapping(path = Constants.V1 + "/stadiums")
	public ResponseEntity<List<StadiumDto>> findAll(@RequestParam(defaultValue = "name,asc") String[] sort) {
		List<Stadium> stadiums = stadiumService.findAll(Sort.by(utilities.buildOrders(sort)));

		if (stadiums.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		List<StadiumDto> stadiumDtos = stadiums.stream().map(mappers::convertToDto).collect(Collectors.toList());
		return new ResponseEntity<>(stadiumDtos, HttpStatus.OK);
	}

	@Operation(summary = OA_GET)
	@JsonView(Views.Public.class)
	@GetMapping(path = Constants.V1 + "/stadiums/{id}")
	public ResponseEntity<StadiumDto> find(@PathVariable("id") Long id) {
		Stadium stadium = stadiumService.findById(id)
				.orElseThrow(() -> new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND));

		StadiumDto stadiumDto = mappers.convertToDto(stadium);
		return new ResponseEntity<>(stadiumDto, HttpStatus.OK);
	}

	@Operation(summary = OA_CREATE)
	@JsonView(Views.Private.class)
	@PostMapping(path = Constants.V1 + "/stadiums")
	public ResponseEntity<StadiumDto> create(@Valid @RequestBody StadiumDto stadiumDto) {
		stadiumDto.setId(null);
		Stadium stadium = mappers.convertToEntity(stadiumDto);
		stadiumDto = mappers.convertToDto(stadiumService.save(stadium));
		return new ResponseEntity<>(stadiumDto, HttpStatus.CREATED);
	}

	@Operation(summary = OA_UPDATE)
	@JsonView(Views.Private.class)
	@PutMapping(path = Constants.V1 + "/stadiums/{id}")
	public ResponseEntity<StadiumDto> update(@PathVariable("id") Long id, @Valid @RequestBody StadiumDto stadiumDto) {
		if (!stadiumService.existsById(id))
			throw new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND);

		stadiumDto.setId(id);
		Stadium stadium = stadiumService.save(mappers.convertToEntity(stadiumDto));
		stadiumDto = mappers.convertToDto(stadium);
		return new ResponseEntity<>(stadiumDto, HttpStatus.OK);
	}

	@Operation(summary = OA_DELETE)
	@JsonView(Views.Private.class)
	@DeleteMapping(path = Constants.V1 + "/stadiums/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		if (!stadiumService.existsById(id))
			throw new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND);

		stadiumService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
