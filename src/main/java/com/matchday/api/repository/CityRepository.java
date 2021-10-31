package com.matchday.api.repository;

import java.util.Optional;

import com.matchday.api.model.entity.City;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByName(String name);

}
