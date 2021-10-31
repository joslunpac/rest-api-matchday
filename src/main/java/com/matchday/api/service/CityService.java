package com.matchday.api.service;

import java.util.List;
import java.util.Optional;

import com.matchday.api.model.entity.City;

import org.springframework.data.domain.Sort;

public interface CityService {

	long count();

	List<City> findAll(Sort sort);

	Optional<City> findById(Long id);

	Optional<City> findByName(String name);

	City save(City city);

	void deleteById(Long id);

	boolean existsById(Long id);

}
