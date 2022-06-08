package com.parceldelivery.security;

import com.parceldelivery.model.security.User;
import com.parceldelivery.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthService authService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        User user = authService.getUser(email);
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(String email) {
        User user = authService.getUser(email);

        return UserPrincipal.create(user);
    }
}
