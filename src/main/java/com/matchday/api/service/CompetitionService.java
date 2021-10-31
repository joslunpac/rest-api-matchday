package com.matchday.api.service;

import java.util.List;
import java.util.Optional;

import com.matchday.api.model.entity.Competition;

import org.springframework.data.domain.Sort;

public interface CompetitionService {

	long count();

	List<Competition> findAll(Sort sort);

	Optional<Competition> findById(Long id);

	Competition save(Competition competition);

	void deleteById(Long id);

	boolean existsById(Long id);

}
