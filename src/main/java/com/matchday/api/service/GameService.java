package com.matchday.api.service;

import java.util.List;
import java.util.Optional;

import com.matchday.api.model.entity.Game;

import org.springframework.data.domain.Sort;

public interface GameService {

	long count();

	List<Game> findAll(Sort sort);

	Optional<Game> findById(Long id);

	Game save(Game game);

	void deleteById(Long id);

	boolean existsById(Long id);

}
