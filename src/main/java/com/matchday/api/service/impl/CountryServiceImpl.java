package com.matchday.api.service.impl;

import java.util.List;
import java.util.Optional;

import com.matchday.api.model.entity.Country;
import com.matchday.api.repository.CountryRepository;
import com.matchday.api.service.CountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	CountryRepository countryRepository;

	@Override
	public long count() {
		return countryRepository.count();
	}

	@Override
	public List<Country> findAll(Sort sort) {
		return countryRepository.findAll(sort);
	}

	@Override
	public Optional<Country> findByAcronym(String acronym) {
		return countryRepository.findByAcronym(acronym);
	}

	@Override
	public Optional<Country> findById(Long id) {
		return countryRepository.findById(id);
	}

	@Override
	@Transactional
	public Country save(Country country) {
		return countryRepository.save(country);
	}

	@Override
	public void deleteById(Long id) {
		countryRepository.deleteById(id);

	}

	@Override
	public boolean existsById(Long id) {
		return countryRepository.existsById(id);
	}

}
