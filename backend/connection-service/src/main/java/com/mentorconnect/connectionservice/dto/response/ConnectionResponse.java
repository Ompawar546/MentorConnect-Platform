package com.mentorconnect.connectionservice.dto.response;

import java.time.LocalDateTime;

import com.mentorconnect.connectionservice.enums.ConnectionStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionResponse {

    private Long id;

    // IDs
    private Long studentUserId;
    private Long mentorUserId;

    // Student Details
    private String studentName;
    private String studentEmail;
    private String studentProfilePicture;

    // Mentor Details
    private String mentorName;
    private String mentorEmail;
    private String mentorProfilePicture;
    private String mentorCompany;
    private String mentorDesignation;
    private Integer mentorExperienceYears;

    // Connection Details
    private ConnectionStatus status;
    private LocalDateTime requestedAt;
    private LocalDateTime respondedAt;
}