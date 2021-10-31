package com.matchday.api.service;

import java.util.List;
import java.util.Optional;

import com.matchday.api.model.entity.Stadium;

import org.springframework.data.domain.Sort;

public interface StadiumService {

	long count();

	List<Stadium> findAll(Sort sort);

	Optional<Stadium> findById(Long id);

	Optional<Stadium> findByName(String name);

	Stadium save(Stadium stadium);

	void deleteById(Long id);

	boolean existsById(Long id);

}
