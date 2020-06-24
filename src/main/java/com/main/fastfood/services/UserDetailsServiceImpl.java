package com.main.fastfood.services;

import com.main.fastfood.models.User;
import com.main.fastfood.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

// Handle UserAuthentication from Database | Personal User's credentials
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

   private final UserRepository userRepository;

   @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ************************ By | Username ************************ //
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the username in the DB
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("No user found " + username));
        // Once found, get the user's username and password and its role.
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                true, true, true, true,
                getAuthorities());
    }

    // ************************* Authorities ************************* //

    private Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }


}
