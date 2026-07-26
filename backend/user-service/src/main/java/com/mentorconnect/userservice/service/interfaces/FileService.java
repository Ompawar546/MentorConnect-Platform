package com.mentorconnect.userservice.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

import com.mentorconnect.userservice.dto.response.FileUploadResponse;

public interface FileService {

    FileUploadResponse uploadProfilePicture(MultipartFile file);

    FileUploadResponse uploadResume(MultipartFile file);

    FileUploadResponse uploadEmploymentProof(MultipartFile file);

}