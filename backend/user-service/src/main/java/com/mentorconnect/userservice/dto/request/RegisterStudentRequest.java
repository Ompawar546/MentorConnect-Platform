package com.mentorconnect.userservice.dto.request;

import com.mentorconnect.userservice.embeddable.SocialLinks;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterStudentRequest extends BaseRegisterRequest {

    @NotBlank(message = "College is required")
    private String college;

    @NotBlank(message = "Degree is required")
    private String degree;

    @NotBlank(message = "Branch is required")
    private String branch;

    @NotNull(message = "Graduation year is required")
    private Integer graduationYear;

    private Integer semester;

    private Double cgpa;

    private String bio;

    @Embedded
    private SocialLinks socialLinks;

    private String resumeUrl;

}