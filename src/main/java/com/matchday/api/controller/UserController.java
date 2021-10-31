package com.matchday.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.matchday.api.exception.ApiForbiddenException;
import com.matchday.api.exception.ApiNotFoundException;
import com.matchday.api.model.dto.security.JwtDto;
import com.matchday.api.model.dto.security.SigninDto;
import com.matchday.api.model.dto.security.SignupDto;
import com.matchday.api.model.entity.Role;
import com.matchday.api.model.entity.User;
import com.matchday.api.model.enumerate.RoleEnum;
import com.matchday.api.security.jwt.JwtUtils;
import com.matchday.api.service.RoleService;
import com.matchday.api.service.UserService;
import com.matchday.api.util.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "A_" + UserController.ENTITIES, description = Constants.OA_ENDPOINT + UserController.ENTITIES)
public class UserController {

	public static final String ENTITY = Constants.USER;
	public static final String ENTITIES = Constants.USERS;

	private static final String OA_AUTHENTICATE = Constants.OA_AUTHENTICATE + ENTITY;
	private static final String OA_REGISTER = Constants.OA_REGISTER + ENTITY;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Operation(summary = OA_AUTHENTICATE)
	@PostMapping(path = "/auth/signin")
	public ResponseEntity<JwtDto> signin(@Valid @RequestBody SigninDto signinDto) {
		// We authenticate the user
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signinDto.getUsername(), signinDto.getPassword()));

		// Once authenticated, we add it to the String Security context
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// We generate the Token
		String jwt = jwtUtils.generarJwt(authentication);

		// We obtain the authenticated user with all their information
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		// We build the answer
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<>(jwtDto, HttpStatus.OK);
	}

	@Operation(summary = OA_REGISTER)
	@PostMapping(path = "/auth/signup")
	public ResponseEntity<HttpStatus> signup(@Valid @RequestBody SignupDto signupDto) {
		// We check that the user does not already exist in BD
		if (userService.existsByUsername(signupDto.getUsername()))
			throw new ApiForbiddenException("The username " + signupDto.getUsername() + Constants.ALREADY_EXISTS);

		if (userService.existsByEmail(signupDto.getEmail()))
			throw new ApiForbiddenException("The email " + signupDto.getEmail() + Constants.ALREADY_EXISTS);

		// We prepare user data
		String name = signupDto.getName().toLowerCase();
		String username = signupDto.getUsername().toLowerCase();
		String email = signupDto.getEmail().toLowerCase();
		String password = passwordEncoder.encode(signupDto.getPassword());

		List<Role> roles = new ArrayList<>();

		if (userService.count() == 0) {
			// If he is the first user in DB, we assign him the roles [USER] and [ADMIN]
			roles.add(roleService.findByName(RoleEnum.ROLE_USER)//
					.orElseThrow(() -> new ApiNotFoundException(
							Constants.ROLE + Constants.WITH_NAME + RoleEnum.ROLE_USER.name() + Constants.NOT_FOUND)));
			roles.add(roleService.findByName(RoleEnum.ROLE_ADMIN)//
					.orElseThrow(() -> new ApiNotFoundException(
							Constants.ROLE + Constants.WITH_NAME + RoleEnum.ROLE_ADMIN.name() + Constants.NOT_FOUND)));
		} else {
			// If there are already one or more users in the DB, we assign the role [USER],
			// so that there can only be one user with the role [ADMIN]
			roles.add(roleService.findByName(RoleEnum.ROLE_USER)//
					.orElseThrow(() -> new ApiNotFoundException(
							Constants.ROLE + Constants.WITH_NAME + RoleEnum.ROLE_USER.name() + Constants.NOT_FOUND)));
		}

		// We build the user and persist it
		userService.save(new User(name, username, email, password, roles));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
