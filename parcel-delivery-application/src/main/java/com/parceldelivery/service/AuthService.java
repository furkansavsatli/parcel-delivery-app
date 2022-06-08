package com.parceldelivery.service;

import com.parceldelivery.dto.LoginRequest;
import com.parceldelivery.dto.SignUpRequest;
import com.parceldelivery.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(url = "${service.gateway}:9991", name = "auth")
public interface AuthService {

    @RequestMapping(method = RequestMethod.POST, value = "auth/signin")
    String signIn(@RequestBody LoginRequest loginRequest);

    @RequestMapping(method = RequestMethod.POST, value = "auth/signup-user")
    Long signupUser(@RequestBody SignUpRequest signUpRequest);

    @RequestMapping(method = RequestMethod.POST, value = "auth/register-courier")
    Long registerCourier(@RequestBody SignUpRequest signUpRequest);

    @RequestMapping(method = RequestMethod.GET, value = "user/courier-list")
    List<User> getCourierList(@RequestHeader("Authorization") String authorization);

}
