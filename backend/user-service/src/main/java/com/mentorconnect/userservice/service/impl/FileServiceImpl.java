package com.mentorconnect.userservice.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mentorconnect.userservice.dto.response.FileUploadResponse;
import com.mentorconnect.userservice.exception.BadRequestException;
import com.mentorconnect.userservice.service.interfaces.FileService;

@Service
public class FileServiceImpl implements FileService {

    private static final String PROFILE_PICTURE_DIR = "uploads/profile-pictures/";
    private static final String RESUME_DIR = "uploads/resumes/";
    private static final String EMPLOYMENT_PROOF_DIR = "uploads/employment-proofs/";

    @Override
    public FileUploadResponse uploadProfilePicture(MultipartFile file) {
        validateImage(file);
        return saveFile(file, PROFILE_PICTURE_DIR);
    }

    @Override
    public FileUploadResponse uploadResume(MultipartFile file) {
        validatePdf(file);
        return saveFile(file, RESUME_DIR);
    }

    @Override
    public FileUploadResponse uploadEmploymentProof(MultipartFile file) {
        validatePdfOrImage(file);
        return saveFile(file, EMPLOYMENT_PROOF_DIR);
    }

    private FileUploadResponse saveFile(MultipartFile file, String uploadDir) {

        try {

            Files.createDirectories(Paths.get(uploadDir));

            String originalFileName = file.getOriginalFilename();

            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

            String fileName = UUID.randomUUID() + extension;

            Path path = Paths.get(uploadDir, fileName);

            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            return FileUploadResponse.builder()
                    .fileName(fileName)
                    .fileUrl(uploadDir + fileName)
                    .message("File uploaded successfully")
                    .build();

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file.");
        }
    }

    private void validateImage(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType == null ||
                !(contentType.equals("image/jpeg")
                        || contentType.equals("image/png"))) {

            throw new BadRequestException("Only JPG and PNG images are allowed.");
        }
    }

    private void validatePdf(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType == null ||
                !contentType.equals("application/pdf")) {

            throw new BadRequestException("Only PDF files are allowed.");
        }
    }

    private void validatePdfOrImage(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType == null ||
                !(contentType.equals("application/pdf")
                        || contentType.equals("image/jpeg")
                        || contentType.equals("image/png"))) {

            throw new BadRequestException("Only PDF, JPG and PNG files are allowed.");
        }
    }
}