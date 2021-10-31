package com.matchday.api.service;

import java.util.Optional;

import com.matchday.api.model.entity.Role;
import com.matchday.api.model.enumerate.RoleEnum;

public interface RoleService {

	long count();

	Optional<Role> findByName(RoleEnum name);

	Role save(Role rol);

}
