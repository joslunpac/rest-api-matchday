package com.matchday.api.service.impl;

import java.util.List;
import java.util.Optional;

import com.matchday.api.model.entity.City;
import com.matchday.api.repository.CityRepository;
import com.matchday.api.service.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	CityRepository cityRepository;

	@Override
	public long count() {
		return cityRepository.count();
	}

	@Override
	public List<City> findAll(Sort sort) {
		return cityRepository.findAll(sort);
	}

	@Override
	public Optional<City> findById(Long id) {
		return cityRepository.findById(id);
	}

	@Override
	public Optional<City> findByName(String name) {
		return cityRepository.findByName(name);
	}

	@Override
	@Transactional
	public City save(City city) {
		return cityRepository.save(city);
	}

	@Override
	public void deleteById(Long id) {
		cityRepository.deleteById(id);

	}

	@Override
	public boolean existsById(Long id) {
		return cityRepository.existsById(id);
	}

}
