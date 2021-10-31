package com.matchday.api.service;

import java.util.Optional;

import com.matchday.api.model.entity.User;

public interface UserService {

	long count();

	Optional<User> findByUsername(String username);

	User save(User user);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

}
