package com.matchday.api.repository;

import com.matchday.api.model.entity.Competition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

}
