package com.matchday.api.service.impl;

import java.util.List;
import java.util.Optional;

import com.matchday.api.model.entity.Competition;
import com.matchday.api.repository.CompetitionRepository;
import com.matchday.api.service.CompetitionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompetitionServiceImpl implements CompetitionService {

	@Autowired
	CompetitionRepository competitionRepository;

	@Override
	public long count() {
		return competitionRepository.count();
	}

	@Override
	public List<Competition> findAll(Sort sort) {
		return competitionRepository.findAll(sort);
	}

	@Override
	public Optional<Competition> findById(Long id) {
		return competitionRepository.findById(id);
	}

	@Override
	@Transactional
	public Competition save(Competition competition) {
		return competitionRepository.save(competition);
	}

	@Override
	public void deleteById(Long id) {
		competitionRepository.deleteById(id);

	}

	@Override
	public boolean existsById(Long id) {
		return competitionRepository.existsById(id);
	}

}
