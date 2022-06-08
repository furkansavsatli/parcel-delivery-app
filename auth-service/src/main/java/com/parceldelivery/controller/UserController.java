package com.parceldelivery.controller;

import com.parceldelivery.model.User;
import com.parceldelivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getUser(@RequestHeader("email") String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(email));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("courier-list")
    public List<User> getCourierList() {
        return userService.getCourierList();
    }

}
