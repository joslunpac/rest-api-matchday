package com.matchday.api.controller.v1;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonView;
import com.matchday.api.exception.ApiNotFoundException;
import com.matchday.api.model.dto.TeamDto;
import com.matchday.api.model.dto.Views;
import com.matchday.api.model.entity.Team;
import com.matchday.api.service.TeamService;
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
@Tag(name = TeamController.ENTITIES, description = Constants.OA_ENDPOINT + TeamController.ENTITIES)
public class TeamController {

	public static final String ENTITY = Constants.TEAM;
	public static final String ENTITIES = Constants.TEAMS;

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
	private TeamService teamService;

	@Operation(summary = OA_GET_ALL)
	@JsonView(Views.Public.class)
	@GetMapping(path = Constants.V1 + "/teams")
	public ResponseEntity<List<TeamDto>> findAll(@RequestParam(defaultValue = "name,asc") String[] sort) {
		List<Team> teams = teamService.findAll(Sort.by(utilities.buildOrders(sort)));

		if (teams.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		List<TeamDto> teamDtos = teams.stream().map(mappers::convertToDto).collect(Collectors.toList());
		return new ResponseEntity<>(teamDtos, HttpStatus.OK);
	}

	@Operation(summary = OA_GET)
	@JsonView(Views.Public.class)
	@GetMapping(path = Constants.V1 + "/teams/{id}")
	public ResponseEntity<TeamDto> find(@PathVariable("id") Long id) {
		Team team = teamService.findById(id)
				.orElseThrow(() -> new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND));

		TeamDto teamDto = mappers.convertToDto(team);
		return new ResponseEntity<>(teamDto, HttpStatus.OK);
	}

	@Operation(summary = OA_CREATE)
	@JsonView(Views.Private.class)
	@PostMapping(path = Constants.V1 + "/teams")
	public ResponseEntity<TeamDto> create(@Valid @RequestBody TeamDto teamDto) {
		teamDto.setId(null);
		Team team = mappers.convertToEntity(teamDto);
		teamDto = mappers.convertToDto(teamService.save(team));
		return new ResponseEntity<>(teamDto, HttpStatus.CREATED);
	}

	@Operation(summary = OA_UPDATE)
	@JsonView(Views.Private.class)
	@PutMapping(path = Constants.V1 + "/teams/{id}")
	public ResponseEntity<TeamDto> update(@PathVariable("id") Long id, @Valid @RequestBody TeamDto teamDto) {
		if (!teamService.existsById(id))
			throw new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND);

		teamDto.setId(id);
		Team team = teamService.save(mappers.convertToEntity(teamDto));
		teamDto = mappers.convertToDto(team);
		return new ResponseEntity<>(teamDto, HttpStatus.OK);
	}

	@Operation(summary = OA_DELETE)
	@JsonView(Views.Private.class)
	@DeleteMapping(path = Constants.V1 + "/teams/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		if (!teamService.existsById(id))
			throw new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND);

		teamService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
