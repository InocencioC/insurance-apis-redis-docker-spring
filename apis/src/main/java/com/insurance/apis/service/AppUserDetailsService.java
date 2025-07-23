package com.insurance.apis.service;

import com.insurance.apis.model.AppUser;
import com.insurance.apis.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

                return new User(user.getUsername(), user.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }
}
