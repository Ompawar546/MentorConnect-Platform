package com.mentorconnect.userservice.dto.request;

import java.util.List;

import com.mentorconnect.userservice.embeddable.Education;
import com.mentorconnect.userservice.embeddable.SocialLinks;
import com.mentorconnect.userservice.enums.AvailabilityStatus;
import com.mentorconnect.userservice.enums.Skill;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateMentorRequest {

    private String currentCompany;

    @Size(max = 2, message = "Maximum 2 previous companies are allowed.")
    private List<String> previousCompanies;

    private String currentDesignation;

    @Max(value = 100)
    private Integer experienceYears;

    @Valid
    private Education education;

    @Size(max = 6, message = "Maximum 6 skills are allowed.")
    private List<Skill> skills;

    private String expertise;

    @Size(max = 1000)
    private String bio;

    private String resumeFileUrl;

    private String employmentProofFileUrl;

    @Valid
    private SocialLinks socialLinks;

    private AvailabilityStatus availabilityStatus;
}