package com.matchday.api.service.impl;

import java.util.List;
import java.util.Optional;

import com.matchday.api.model.entity.State;
import com.matchday.api.repository.StateRepository;
import com.matchday.api.service.StateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StateServiceImpl implements StateService {

	@Autowired
	StateRepository stateRepository;

	@Override
	public long count() {
		return stateRepository.count();
	}

	@Override
	public List<State> findAll(Sort sort) {
		return stateRepository.findAll(sort);
	}

	@Override
	public Optional<State> findById(Long id) {
		return stateRepository.findById(id);
	}

	@Override
	public Optional<State> findByAcronym(String acronym) {
		return stateRepository.findByAcronym(acronym);
	}

	@Override
	@Transactional
	public State save(State state) {
		return stateRepository.save(state);
	}

	@Override
	public void deleteById(Long id) {
		stateRepository.deleteById(id);

	}

	@Override
	public boolean existsById(Long id) {
		return stateRepository.existsById(id);
	}

}
