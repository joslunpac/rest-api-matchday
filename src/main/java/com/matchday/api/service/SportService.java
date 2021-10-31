package com.matchday.api.service;

import java.util.List;
import java.util.Optional;

import com.matchday.api.model.entity.Sport;

import org.springframework.data.domain.Sort;

public interface SportService {

	long count();

	List<Sport> findAll(Sort sort);

	Optional<Sport> findById(Long id);

	Optional<Sport> findByName(String name);

	Sport save(Sport sport);

	void deleteById(Long id);

	boolean existsById(Long id);

}
