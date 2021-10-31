package com.matchday.api.repository;

import java.util.Optional;

import com.matchday.api.model.entity.Role;
import com.matchday.api.model.enumerate.RoleEnum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleEnum name);

}
