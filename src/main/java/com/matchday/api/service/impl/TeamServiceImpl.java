package com.matchday.api.service.impl;

import java.util.List;
import java.util.Optional;

import com.matchday.api.model.entity.Team;
import com.matchday.api.repository.TeamRepository;
import com.matchday.api.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	TeamRepository teamRepository;

	@Override
	public long count() {
		return teamRepository.count();
	}

	@Override
	public List<Team> findAll(Sort sort) {
		return teamRepository.findAll(sort);
	}

	@Override
	public Optional<Team> findById(Long id) {
		return teamRepository.findById(id);
	}

	@Override
	@Transactional
	public Team save(Team team) {
		return teamRepository.save(team);
	}

	@Override
	public void deleteById(Long id) {
		teamRepository.deleteById(id);

	}

	@Override
	public boolean existsById(Long id) {
		return teamRepository.existsById(id);
	}

}
