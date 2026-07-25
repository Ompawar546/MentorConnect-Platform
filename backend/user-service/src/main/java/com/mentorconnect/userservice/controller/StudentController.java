package com.mentorconnect.userservice.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mentorconnect.userservice.dto.request.UpdateStudentRequest;
import com.mentorconnect.userservice.dto.response.StudentResponse;
import com.mentorconnect.userservice.service.interfaces.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/me")
    public ResponseEntity<StudentResponse> getCurrentStudent(
            Principal principal) {

        return ResponseEntity.ok(
                studentService.getCurrentStudent(principal.getName()));
    }

    @PutMapping("/me")
    public ResponseEntity<StudentResponse> updateCurrentStudent(
            Principal principal,
            @Valid @RequestBody UpdateStudentRequest request) {

        return ResponseEntity.ok( studentService.updateCurrentStudent( principal.getName(), request));
    }
}