package com.malinovski.helpdesk.service;

import com.malinovski.helpdesk.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userService.getUserByEmail(email);
        String mail = "user error";
        String password = "password error";

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (user != null) {
            String role = user.getRole().toString();
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
            mail = user.getEmail();
            password = user.getPassword();

        }
        return new org.springframework.security.core.userdetails.User(mail, password, grantedAuthorities);
    }
}

