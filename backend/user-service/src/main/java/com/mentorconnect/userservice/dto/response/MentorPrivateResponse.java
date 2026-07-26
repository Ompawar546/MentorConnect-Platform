package com.mentorconnect.userservice.dto.response;

import java.util.List;

import com.mentorconnect.userservice.embeddable.Education;
import com.mentorconnect.userservice.embeddable.SocialLinks;
import com.mentorconnect.userservice.enums.AvailabilityStatus;
import com.mentorconnect.userservice.enums.Skill;

import lombok.Data;

@Data
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

    private Education education;

    private List<Skill> skills;

    private String expertise;

    private String bio;

    private String resumeFileUrl;

    private SocialLinks socialLinks;

    private AvailabilityStatus availabilityStatus;

    private Double averageRating;

    private Boolean verified;
}