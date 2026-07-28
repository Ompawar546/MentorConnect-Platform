package com.mentorconnect.userservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mentorconnect.userservice.dto.request.UpdateMentorRequest;
import com.mentorconnect.userservice.dto.response.MentorCardResponse;
import com.mentorconnect.userservice.dto.response.MentorPrivateResponse;
import com.mentorconnect.userservice.dto.response.MentorPublicResponse;
import com.mentorconnect.userservice.dto.response.UserIdResponse;
import com.mentorconnect.userservice.entity.MentorProfile;
import com.mentorconnect.userservice.entity.User;
import com.mentorconnect.userservice.enums.Skill;
import com.mentorconnect.userservice.exception.ResourceNotFoundException;
import com.mentorconnect.userservice.mapper.MentorMapper;
import com.mentorconnect.userservice.repository.MentorProfileRepository;
import com.mentorconnect.userservice.repository.UserRepository;
import com.mentorconnect.userservice.service.interfaces.MentorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {

    private final MentorProfileRepository mentorProfileRepository;
    private final UserRepository userRepository;
    private final MentorMapper mentorMapper;

    @Override
    public MentorPrivateResponse getMyProfile() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        MentorProfile mentorProfile = mentorProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Mentor profile not found."));

        return mentorMapper.toPrivateResponse(mentorProfile);
    }

    @Override
    public MentorPrivateResponse updateMyProfile(UpdateMentorRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        MentorProfile mentorProfile = mentorProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Mentor profile not found."));

        mentorMapper.updateMentorProfile(request, mentorProfile);

        MentorProfile updatedProfile = mentorProfileRepository.save(mentorProfile);

        return mentorMapper.toPrivateResponse(updatedProfile);
    }

    @Override
    public List<MentorCardResponse> getAllMentors() {

        return mentorProfileRepository.findAll()
                .stream()
                .map(mentorMapper::toCardResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MentorPublicResponse getMentorById(Long mentorId) {

        MentorProfile mentorProfile = mentorProfileRepository.findById(mentorId)
                .orElseThrow(() -> new ResourceNotFoundException("Mentor profile not found."));

        return mentorMapper.toPublicResponse(mentorProfile);
    }
    
    
    @Override
    public List<MentorCardResponse> filterMentors(
            Skill skill,
            String company,
            Boolean verified,
            Integer experienceYears) {

        List<MentorProfile> mentors;

        if (skill != null) {
            mentors = mentorProfileRepository.findBySkillsContaining(skill);

        } else if (company != null && !company.isBlank()) {
            mentors = mentorProfileRepository.findByCurrentCompanyContainingIgnoreCase(company);

        } else if (verified != null) {
            mentors = mentorProfileRepository.findByVerified(verified);

        } else if (experienceYears != null) {
            mentors = mentorProfileRepository.findByExperienceYearsGreaterThanEqual(experienceYears);

        } else {
            mentors = mentorProfileRepository.findAll();
        }

        return mentors.stream()
                .map(mentorMapper::toCardResponse)
                .toList();
    }
    
    
    @Override
    public UserIdResponse getMentorUserId(Long mentorProfileId) {

        MentorProfile mentorProfile = mentorProfileRepository.findById(mentorProfileId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Mentor profile not found."));

        return new UserIdResponse(mentorProfile.getUser().getId());
    }
    
    @Override
    public MentorPrivateResponse getPrivateProfileByUserId(Long userId) {

        MentorProfile mentorProfile = mentorProfileRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Mentor profile not found."));

        return mentorMapper.toPrivateResponse(mentorProfile);
    }
    
}