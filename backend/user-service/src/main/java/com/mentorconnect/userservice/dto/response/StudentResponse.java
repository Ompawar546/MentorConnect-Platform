package com.mentorconnect.userservice.dto.response;

import com.mentorconnect.userservice.embeddable.SocialLinks;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentResponse {

    // User Details
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String profilePictureUrl;

    // Student Details
    private String college;
    private String degree;
    private String branch;
    private Integer graduationYear;
    private Integer semester;
    private Double cgpa;
    private String bio;
    private SocialLinks socialLinks;
    private String resumeUrl;
}