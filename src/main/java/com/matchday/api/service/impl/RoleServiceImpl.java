package com.matchday.api.service.impl;

import java.util.Optional;

import com.matchday.api.model.entity.Role;
import com.matchday.api.model.enumerate.RoleEnum;
import com.matchday.api.repository.RoleRepository;
import com.matchday.api.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public long count() {
		return roleRepository.count();
	}

	@Override
	public Optional<Role> findByName(RoleEnum name) {
		return roleRepository.findByName(name);
	}

	@Override
	@Transactional
	public Role save(Role role) {
		return roleRepository.save(role);
	}

}
