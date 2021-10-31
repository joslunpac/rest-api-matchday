package com.matchday.api.controller.v1;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonView;
import com.matchday.api.exception.ApiNotFoundException;
import com.matchday.api.model.dto.CountryDto;
import com.matchday.api.model.dto.Views;
import com.matchday.api.model.entity.Country;
import com.matchday.api.service.CountryService;
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
@Tag(name = CountryController.ENTITIES, description = Constants.OA_ENDPOINT + CountryController.ENTITIES)
public class CountryController {

	public static final String ENTITY = Constants.COUNTRY;
	public static final String ENTITIES = Constants.COUNTRIES;

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
	private CountryService countryService;

	@Operation(summary = OA_GET_ALL)
	@JsonView(Views.Public.class)
	@GetMapping(path = Constants.V1 + "/countries")
	public ResponseEntity<List<CountryDto>> findAll(@RequestParam(defaultValue = "name,asc") String[] sort) {
		List<Country> countries = countryService.findAll(Sort.by(utilities.buildOrders(sort)));

		if (countries.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		List<CountryDto> countryDtos = countries.stream().map(mappers::convertToDto).collect(Collectors.toList());
		return new ResponseEntity<>(countryDtos, HttpStatus.OK);
	}

	@Operation(summary = OA_GET)
	@JsonView(Views.Public.class)
	@GetMapping(path = Constants.V1 + "/countries/{id}")
	public ResponseEntity<CountryDto> find(@PathVariable("id") Long id) {
		Country country = countryService.findById(id)
				.orElseThrow(() -> new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND));

		CountryDto countryDto = mappers.convertToDto(country);
		return new ResponseEntity<>(countryDto, HttpStatus.OK);
	}

	@Operation(summary = OA_CREATE)
	@JsonView(Views.Private.class)
	@PostMapping(path = Constants.V1 + "/countries")
	public ResponseEntity<CountryDto> create(@Valid @RequestBody CountryDto countryDto) {
		countryDto.setId(null);
		Country country = mappers.convertToEntity(countryDto);
		countryDto = mappers.convertToDto(countryService.save(country));
		return new ResponseEntity<>(countryDto, HttpStatus.CREATED);
	}

	@Operation(summary = OA_UPDATE)
	@JsonView(Views.Private.class)
	@PutMapping(path = Constants.V1 + "/countries/{id}")
	public ResponseEntity<CountryDto> update(@PathVariable("id") Long id, @Valid @RequestBody CountryDto countryDto) {
		if (!countryService.existsById(id))
			throw new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND);

		countryDto.setId(id);
		Country country = countryService.save(mappers.convertToEntity(countryDto));
		countryDto = mappers.convertToDto(country);
		return new ResponseEntity<>(countryDto, HttpStatus.OK);
	}

	@Operation(summary = OA_DELETE)
	@JsonView(Views.Private.class)
	@DeleteMapping(path = Constants.V1 + "/countries/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		if (!countryService.existsById(id))
			throw new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND);

		countryService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
