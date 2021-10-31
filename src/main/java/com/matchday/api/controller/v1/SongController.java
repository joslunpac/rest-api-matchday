package com.matchday.api.controller.v1;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonView;
import com.matchday.api.exception.ApiNotFoundException;
import com.matchday.api.model.dto.SongDto;
import com.matchday.api.model.dto.Views;
import com.matchday.api.model.entity.Song;
import com.matchday.api.service.SongService;
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
@Tag(name = SongController.ENTITIES, description = Constants.OA_ENDPOINT + SongController.ENTITIES)
public class SongController {

	public static final String ENTITY = Constants.SONG;
	public static final String ENTITIES = Constants.SONGS;

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
	private SongService songService;

	@Operation(summary = OA_GET_ALL)
	@JsonView(Views.Public.class)
	@GetMapping(path = Constants.V1 + "/songs")
	public ResponseEntity<List<SongDto>> findAll(@RequestParam(defaultValue = "name,asc") String[] sort) {
		List<Song> songs = songService.findAll(Sort.by(utilities.buildOrders(sort)));

		if (songs.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		List<SongDto> songDtos = songs.stream().map(mappers::convertToDto).collect(Collectors.toList());
		return new ResponseEntity<>(songDtos, HttpStatus.OK);
	}

	@Operation(summary = OA_GET)
	@JsonView(Views.Public.class)
	@GetMapping(path = Constants.V1 + "/songs/{id}")
	public ResponseEntity<SongDto> find(@PathVariable("id") Long id) {
		Song song = songService.findById(id)
				.orElseThrow(() -> new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND));

		SongDto songDto = mappers.convertToDto(song);
		return new ResponseEntity<>(songDto, HttpStatus.OK);
	}

	@Operation(summary = OA_CREATE)
	@JsonView(Views.Private.class)
	@PostMapping(path = Constants.V1 + "/songs")
	public ResponseEntity<SongDto> create(@Valid @RequestBody SongDto songDto) {
		songDto.setId(null);
		Song song = mappers.convertToEntity(songDto);
		songDto = mappers.convertToDto(songService.save(song));
		return new ResponseEntity<>(songDto, HttpStatus.CREATED);
	}

	@Operation(summary = OA_UPDATE)
	@JsonView(Views.Private.class)
	@PutMapping(path = Constants.V1 + "/songs/{id}")
	public ResponseEntity<SongDto> update(@PathVariable("id") Long id, @Valid @RequestBody SongDto songDto) {
		if (!songService.existsById(id))
			throw new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND);

		songDto.setId(id);
		Song song = songService.save(mappers.convertToEntity(songDto));
		songDto = mappers.convertToDto(song);
		return new ResponseEntity<>(songDto, HttpStatus.OK);
	}

	@Operation(summary = OA_DELETE)
	@JsonView(Views.Private.class)
	@DeleteMapping(path = Constants.V1 + "/songs/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		if (!songService.existsById(id))
			throw new ApiNotFoundException(ENTITY + Constants.WITH_ID + id + Constants.NOT_FOUND);

		songService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
