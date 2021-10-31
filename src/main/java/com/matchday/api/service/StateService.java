package com.matchday.api.service;

import java.util.List;
import java.util.Optional;

import com.matchday.api.model.entity.State;

import org.springframework.data.domain.Sort;

public interface StateService {

	long count();

	List<State> findAll(Sort sort);

	Optional<State> findById(Long id);

	Optional<State> findByAcronym(String acronym);

	State save(State state);

	void deleteById(Long id);

	boolean existsById(Long id);

}
