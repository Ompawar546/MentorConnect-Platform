package com.mentorconnect.connectionservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    private Long userId;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String profilePictureUrl;

    private String college;

    private String degree;

    private String branch;

    private Integer graduationYear;

    private Integer semester;

    private Double cgpa;

    private String bio;

    private Object socialLinks;

    private String resumeUrl;
}