package com.matchday.api.service;

import java.util.List;
import java.util.Optional;

import com.matchday.api.model.entity.Country;

import org.springframework.data.domain.Sort;

public interface CountryService {

	long count();

	List<Country> findAll(Sort sort);

	Optional<Country> findById(Long id);

	Optional<Country> findByAcronym(String acronym);

	Country save(Country country);

	void deleteById(Long id);

	boolean existsById(Long id);

}
