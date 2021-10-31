package com.matchday.api.service.impl;

import java.util.List;
import java.util.Optional;

import com.matchday.api.model.entity.Stadium;
import com.matchday.api.repository.StadiumRepository;
import com.matchday.api.service.StadiumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StadiumServiceImpl implements StadiumService {

	@Autowired
	StadiumRepository stadiumRepository;

	@Override
	public long count() {
		return stadiumRepository.count();
	}

	@Override
	public List<Stadium> findAll(Sort sort) {
		return stadiumRepository.findAll(sort);
	}

	@Override
	public Optional<Stadium> findById(Long id) {
		return stadiumRepository.findById(id);
	}

	@Override
	public Optional<Stadium> findByName(String name) {
		return stadiumRepository.findByName(name);
	}

	@Override
	@Transactional
	public Stadium save(Stadium stadium) {
		return stadiumRepository.save(stadium);
	}

	@Override
	public void deleteById(Long id) {
		stadiumRepository.deleteById(id);

	}

	@Override
	public boolean existsById(Long id) {
		return stadiumRepository.existsById(id);
	}

}
