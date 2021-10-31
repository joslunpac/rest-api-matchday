package com.matchday.api.security.service;

import com.matchday.api.model.entity.User;
import com.matchday.api.repository.UserRepository;
import com.matchday.api.util.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                Constants.USER + Constants.WITH_NAME + username + Constants.NOT_FOUND));

        return UserDetailsImpl.construir(usuario);
    }

}
