package com.matchday.api.repository;

import java.util.Optional;

import com.matchday.api.model.entity.Sport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {

    Optional<Sport> findByName(String name);

}
