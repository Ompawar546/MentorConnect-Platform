package com.mentorconnect.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mentorconnect.userservice.dto.response.StudentResponse;
import com.mentorconnect.userservice.service.interfaces.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/internal/students")
@RequiredArgsConstructor
public class InternalStudentController {

    private final StudentService studentService;

    @GetMapping("/user/{userId}/private")
    public ResponseEntity<StudentResponse> getPrivateProfile(
            @PathVariable Long userId) {

        return ResponseEntity.ok(studentService.getStudentById(userId));
    }
}