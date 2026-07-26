package com.mentorconnect.userservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mentorconnect.userservice.entity.MentorProfile;
import com.mentorconnect.userservice.enums.Skill;

public interface MentorProfileRepository extends JpaRepository<MentorProfile, Long> {

    Optional<MentorProfile> findByUserId(Long userId);
    
    
    List<MentorProfile> findBySkillsContaining(Skill skill);

    List<MentorProfile> findByCurrentCompanyContainingIgnoreCase(String company);

    List<MentorProfile> findByVerified(boolean verified);

    List<MentorProfile> findByExperienceYearsGreaterThanEqual(Integer experienceYears);

}