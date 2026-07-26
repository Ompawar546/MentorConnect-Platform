package com.mentorconnect.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mentorconnect.userservice.dto.response.FileUploadResponse;
import com.mentorconnect.userservice.service.interfaces.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/profile-picture")
    @PreAuthorize("hasAnyRole('STUDENT','MENTOR')")
    public ResponseEntity<FileUploadResponse> uploadProfilePicture(
            @RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok(fileService.uploadProfilePicture(file));
    }

    @PostMapping("/resume")
    @PreAuthorize("hasAnyRole('STUDENT','MENTOR')")
    public ResponseEntity<FileUploadResponse> uploadResume(
            @RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok(fileService.uploadResume(file));
    }

    @PostMapping("/employment-proof")
    @PreAuthorize("hasRole('MENTOR')")
    public ResponseEntity<FileUploadResponse> uploadEmploymentProof(
            @RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok(fileService.uploadEmploymentProof(file));
    }

}