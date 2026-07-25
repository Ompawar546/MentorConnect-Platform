package com.mentorconnect.userservice.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mentorconnect.userservice.security.CustomUserDetails;

@RestController
public class TestController {

    @GetMapping("/api/test")
    public String test(Authentication authentication) {

        CustomUserDetails user =
                (CustomUserDetails) authentication.getPrincipal();

        return "JWT Authentication Working!\n\n"
                + "Email : " + user.getUsername()
                + "\nRole : " + user.getAuthorities();
    }
}