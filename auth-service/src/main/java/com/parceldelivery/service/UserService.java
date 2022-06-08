package com.parceldelivery.service;

import com.parceldelivery.model.RoleName;
import com.parceldelivery.model.User;
import com.parceldelivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(String email) {
        return userRepository.findByEmail(email).get();
    }

    public List<User> getCourierList() {
        return userRepository.findAll().stream().filter(user -> user.getRoles().iterator().next().getName().equals(RoleName.ROLE_COURIER)).collect(Collectors.toList());
    }
}
