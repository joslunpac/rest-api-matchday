package com.matchday.api.repository;

import java.util.Optional;

import com.matchday.api.model.entity.Country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByAcronym(String acronym);

}
