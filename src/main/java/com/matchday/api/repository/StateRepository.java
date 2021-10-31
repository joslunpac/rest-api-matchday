package com.matchday.api.repository;

import java.util.Optional;

import com.matchday.api.model.entity.State;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    Optional<State> findByAcronym(String acronym);

}
