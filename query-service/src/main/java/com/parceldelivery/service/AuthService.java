package com.parceldelivery.service;

import com.parceldelivery.model.security.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${service.auth}:8081", name = "auth")
public interface AuthService {

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    User getUser(@RequestHeader String email);
}
