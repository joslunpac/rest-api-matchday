package com.matchday.api.repository;

import java.util.Optional;

import com.matchday.api.model.entity.Stadium;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Long>, JpaSpecificationExecutor<Stadium> {

    Optional<Stadium> findByName(String name);

}
