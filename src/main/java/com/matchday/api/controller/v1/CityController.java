package com.matchday.api.controller.v1;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonView;
import com.matchday.api.exception.ApiNotFoundException;
import com.matchday.api.model.dto.CityDto;
import com.matchday.api.model.dto.Views;
import com.matchday.api.model.entity.City;
import com.matchday.api.service.CityService;
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
@Tag(name = CityController.ENTITIES, description = Constants.OA_ENDPOINT + CityController.ENTITIES)
public class CityController {

	public static final String ENTITY = Constants.CITY;
	public static final String ENTITIES = Constants.CITIES;

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
	private CityService cityService;

	@Operation(summary = OA_GET_ALL)
	@JsonView(Views.Public.class)
	@GetMapping(path = Constants.V1 + "/cities")
	public ResponseEntity<List<CityDto>> findAll(@RequestParam(defaultValue = "name,asc") String[] sort) {
		List<City> cities = cityService.findAll(Sort.by(utilities.buildOrders(sort)));

		if (cities.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		List<CityDto> cityDtos = cities.stream().map(mappers::convertToDto).collect(Collectors.toList());
		return new ResponseEntity<>(cityDtos, HttpStatus.OK);
	}

	@Operation(summary = OA_GET)
	@JsonView(Views.Public.class)
	@GetMapping(path = Constants.V1 + "/cities/{id}")
	public ResponseEntity<CityDto> find(@PathVariable("id") Long id) {
		City city = cityService.findById(id)
				.orElseThrow(() -> new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND));

		CityDto cityDto = mappers.convertToDto(city);
		return new ResponseEntity<>(cityDto, HttpStatus.OK);
	}

	@Operation(summary = OA_CREATE)
	@JsonView(Views.Private.class)
	@PostMapping(path = Constants.V1 + "/cities")
	public ResponseEntity<CityDto> create(@Valid @RequestBody CityDto cityDto) {
		cityDto.setId(null);
		City city = mappers.convertToEntity(cityDto);
		cityDto = mappers.convertToDto(cityService.save(city));
		return new ResponseEntity<>(cityDto, HttpStatus.CREATED);
	}

	@Operation(summary = OA_UPDATE)
	@JsonView(Views.Private.class)
	@PutMapping(path = Constants.V1 + "/cities/{id}")
	public ResponseEntity<CityDto> update(@PathVariable("id") Long id, @Valid @RequestBody CityDto cityDto) {
		if (!cityService.existsById(id))
			throw new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND);

		cityDto.setId(id);
		City city = cityService.save(mappers.convertToEntity(cityDto));
		cityDto = mappers.convertToDto(city);
		return new ResponseEntity<>(cityDto, HttpStatus.OK);
	}

	@Operation(summary = OA_DELETE)
	@JsonView(Views.Private.class)
	@DeleteMapping(path = Constants.V1 + "/cities/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		if (!cityService.existsById(id))
			throw new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND);

		cityService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
