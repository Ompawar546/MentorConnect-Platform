package com.mentorconnect.userservice.dto.response;

import java.util.List;

import com.mentorconnect.userservice.enums.Skill;

import lombok.Data;

@Data
public class MentorCardResponse {

    private Long id;

    private String profilePictureUrl;

    private String currentCompany;

    private String currentDesignation;

    private Integer experienceYears;

    private List<Skill> skills;

    private Double averageRating;

    private Boolean verified;
}