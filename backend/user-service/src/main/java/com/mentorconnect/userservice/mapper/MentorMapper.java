package com.mentorconnect.userservice.mapper;

import org.springframework.stereotype.Component;

import com.mentorconnect.userservice.dto.request.UpdateMentorRequest;
import com.mentorconnect.userservice.dto.response.MentorCardResponse;
import com.mentorconnect.userservice.dto.response.MentorPrivateResponse;
import com.mentorconnect.userservice.dto.response.MentorPublicResponse;
import com.mentorconnect.userservice.entity.MentorProfile;

@Component
public class MentorMapper {

    public MentorCardResponse toCardResponse(MentorProfile mentorProfile) {

        MentorCardResponse response = new MentorCardResponse();

        response.setId(mentorProfile.getId());
        response.setCurrentCompany(mentorProfile.getCurrentCompany());
        response.setCurrentDesignation(mentorProfile.getCurrentDesignation());
        response.setExperienceYears(mentorProfile.getExperienceYears());
        response.setSkills(mentorProfile.getSkills());
        response.setAverageRating(mentorProfile.getAverageRating());
        response.setVerified(mentorProfile.getVerified());

        if (mentorProfile.getUser() != null) {
            response.setProfilePictureUrl(mentorProfile.getUser().getProfilePictureUrl());
        }

        return response;
    }

    public MentorPublicResponse toPublicResponse(MentorProfile mentorProfile) {

        MentorPublicResponse response = new MentorPublicResponse();

        response.setId(mentorProfile.getId());
        response.setCurrentCompany(mentorProfile.getCurrentCompany());
        response.setCurrentDesignation(mentorProfile.getCurrentDesignation());
        response.setExperienceYears(mentorProfile.getExperienceYears());
        response.setSkills(mentorProfile.getSkills());
        response.setAverageRating(mentorProfile.getAverageRating());
        response.setVerified(mentorProfile.getVerified());

        if (mentorProfile.getUser() != null) {
            response.setProfilePictureUrl(mentorProfile.getUser().getProfilePictureUrl());
        }

        return response;
    }

    public MentorPrivateResponse toPrivateResponse(MentorProfile mentorProfile) {

        MentorPrivateResponse response = new MentorPrivateResponse();

        response.setId(mentorProfile.getId());

        if (mentorProfile.getUser() != null) {

            response.setUsername(mentorProfile.getUser().getUsername());
            response.setFirstName(mentorProfile.getUser().getFirstName());
            response.setLastName(mentorProfile.getUser().getLastName());
            response.setEmail(mentorProfile.getUser().getEmail());
            response.setPhone(mentorProfile.getUser().getPhone());
            response.setProfilePictureUrl(mentorProfile.getUser().getProfilePictureUrl());
        }

        response.setCurrentCompany(mentorProfile.getCurrentCompany());
        response.setPreviousCompanies(mentorProfile.getPreviousCompanies());
        response.setCurrentDesignation(mentorProfile.getCurrentDesignation());
        response.setExperienceYears(mentorProfile.getExperienceYears());
        response.setEducation(mentorProfile.getEducation());
        response.setSkills(mentorProfile.getSkills());
        response.setExpertise(mentorProfile.getExpertise());
        response.setBio(mentorProfile.getBio());
        response.setResumeFileUrl(mentorProfile.getResumeFileUrl());
        response.setSocialLinks(mentorProfile.getSocialLinks());
        response.setAvailabilityStatus(mentorProfile.getAvailabilityStatus());
        response.setAverageRating(mentorProfile.getAverageRating());
        response.setVerified(mentorProfile.getVerified());

        return response;
    }

    public void updateMentorProfile(UpdateMentorRequest request, MentorProfile mentorProfile) {

        mentorProfile.setCurrentCompany(request.getCurrentCompany());
        mentorProfile.setPreviousCompanies(request.getPreviousCompanies());
        mentorProfile.setCurrentDesignation(request.getCurrentDesignation());
        mentorProfile.setExperienceYears(request.getExperienceYears());
        mentorProfile.setEducation(request.getEducation());
        mentorProfile.setSkills(request.getSkills());
        mentorProfile.setExpertise(request.getExpertise());
        mentorProfile.setBio(request.getBio());
        mentorProfile.setResumeFileUrl(request.getResumeFileUrl());
        mentorProfile.setEmploymentProofFileUrl(request.getEmploymentProofFileUrl());
        mentorProfile.setSocialLinks(request.getSocialLinks());
        mentorProfile.setAvailabilityStatus(request.getAvailabilityStatus());
    }
}