package com.mentorconnect.connectionservice.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MentorPrivateResponse {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String profilePictureUrl;

    private String currentCompany;

    private List<String> previousCompanies;

    private String currentDesignation;

    private Integer experienceYears;

    private Object education;

    private List<String> skills;

    private String expertise;

    private String bio;

    private String resumeFileUrl;

    private Object socialLinks;

    private String availabilityStatus;

    private Double averageRating;

    private Boolean verified;
}