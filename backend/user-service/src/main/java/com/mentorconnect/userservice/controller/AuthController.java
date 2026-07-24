package com.mentorconnect.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mentorconnect.userservice.dto.request.RegisterMentorRequest;
import com.mentorconnect.userservice.dto.request.RegisterStudentRequest;
import com.mentorconnect.userservice.dto.response.RegisterResponse;
import com.mentorconnect.userservice.service.interfaces.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register/student")
    public ResponseEntity<RegisterResponse> registerStudent(
            @Valid @RequestBody RegisterStudentRequest request) {

        RegisterResponse response = authService.registerStudent(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/register/mentor")
    public ResponseEntity<RegisterResponse> registerMentor(
            @Valid @RequestBody RegisterMentorRequest request) {

        RegisterResponse response = authService.registerMentor(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}