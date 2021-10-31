package com.matchday.api.service.impl;

import java.util.List;
import java.util.Optional;

import com.matchday.api.model.entity.Sport;
import com.matchday.api.repository.SportRepository;
import com.matchday.api.service.SportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SportServiceImpl implements SportService {

	@Autowired
	SportRepository sportRepository;

	@Override
	public long count() {
		return sportRepository.count();
	}

	@Override
	public List<Sport> findAll(Sort sort) {
		return sportRepository.findAll(sort);
	}

	@Override
	public Optional<Sport> findById(Long id) {
		return sportRepository.findById(id);
	}

	@Override
	public Optional<Sport> findByName(String name) {
		return sportRepository.findByName(name);
	}

	@Override
	@Transactional
	public Sport save(Sport sport) {
		return sportRepository.save(sport);
	}

	@Override
	public void deleteById(Long id) {
		sportRepository.deleteById(id);

	}

	@Override
	public boolean existsById(Long id) {
		return sportRepository.existsById(id);
	}

}
