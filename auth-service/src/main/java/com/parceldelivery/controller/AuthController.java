package com.parceldelivery.controller;

import com.parceldelivery.dto.LoginRequest;
import com.parceldelivery.dto.SignUpRequest;
import com.parceldelivery.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    @ResponseStatus(OK)
    public String login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup-user")
    @ResponseStatus(OK)
    public Long registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/register-courier")
    @ResponseStatus(OK)
    public Long registerCourier(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.registerCourier(signUpRequest);
    }

    @GetMapping(value = "validate")
    public ResponseEntity getJWTToken(@RequestHeader("Authorization") String apiKey) {
        authService.validateApiKeyAndGetJwtToken(apiKey);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
