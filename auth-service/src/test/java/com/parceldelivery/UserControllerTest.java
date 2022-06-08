package com.parceldelivery;


import com.parceldelivery.dto.LoginRequest;
import com.parceldelivery.dto.SignUpRequest;
import com.parceldelivery.model.User;
import com.parceldelivery.repository.UserRepository;
import com.parceldelivery.security.JwtTokenProvider;
import com.parceldelivery.service.AuthService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp() {

        User admin = new User();
        admin.setEmail("admin");
        admin.setName("user");
        admin.setPassword("user");

        SignUpRequest signUpRequest = SignUpRequest.builder().password("userTest").email("userTest@gmail.com").name("userTest").build();

        authService.registerUser(signUpRequest);
    }

    @AfterEach
    public void deleteDb() {

        Optional<User> byEmail = userRepository.findByEmail("userTest@gmail.com");
        userRepository.deleteById(byEmail.get().getId());
    }

    @Test
    public void testAuth() throws Exception {
        String token = authService.authenticateUser(LoginRequest.builder().password("userTest").email("userTest@gmail.com").build());
        Assertions.assertNotNull(token);
        mockMvc.perform(get("/user/courier-list")).andExpect(status().isForbidden());

        String adminToken = authService.authenticateUser(LoginRequest.builder().password("12345678").email("admin@gmail.com").build());
        mockMvc.perform(get("/user/courier-list").header("Authorization", adminToken)).andExpect(status().isOk());
    }


}