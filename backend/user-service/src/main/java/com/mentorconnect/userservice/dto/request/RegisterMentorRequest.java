package com.mentorconnect.userservice.dto.request;

import com.mentorconnect.userservice.embeddable.SocialLinks;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterMentorRequest extends BaseRegisterRequest {

    @NotBlank(message = "Company is required")
    private String company;

    @NotBlank(message = "Designation is required")
    private String designation;

    @NotNull(message = "Experience is required")
    private Integer experienceYears;

    @NotBlank(message = "Expertise is required")
    private String expertise;

    private String bio;

    @Embedded
    private SocialLinks socialLinks;

    private String website;

}