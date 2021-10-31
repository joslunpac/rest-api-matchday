package com.matchday.api.service.impl;

import java.util.List;
import java.util.Optional;

import com.matchday.api.model.entity.Game;
import com.matchday.api.repository.GameRepository;
import com.matchday.api.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	GameRepository gameRepository;

	@Override
	public long count() {
		return gameRepository.count();
	}

	@Override
	public List<Game> findAll(Sort sort) {
		return gameRepository.findAll(sort);
	}

	@Override
	public Optional<Game> findById(Long id) {
		return gameRepository.findById(id);
	}

	@Override
	@Transactional
	public Game save(Game game) {
		return gameRepository.save(game);
	}

	@Override
	public void deleteById(Long id) {
		gameRepository.deleteById(id);

	}

	@Override
	public boolean existsById(Long id) {
		return gameRepository.existsById(id);
	}

}
