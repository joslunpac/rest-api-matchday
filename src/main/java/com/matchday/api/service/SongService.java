package com.matchday.api.service;

import java.util.List;
import java.util.Optional;

import com.matchday.api.model.entity.Song;

import org.springframework.data.domain.Sort;

public interface SongService {

	long count();

	List<Song> findAll(Sort sort);

	Optional<Song> findById(Long id);

	Song save(Song song);

	void deleteById(Long id);

	boolean existsById(Long id);

}
