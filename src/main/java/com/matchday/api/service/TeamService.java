package com.matchday.api.service;

import java.util.List;
import java.util.Optional;

import com.matchday.api.model.entity.Team;

import org.springframework.data.domain.Sort;

public interface TeamService {

	long count();

	List<Team> findAll(Sort sort);

	Optional<Team> findById(Long id);

	Team save(Team team);

	void deleteById(Long id);

	boolean existsById(Long id);

}
