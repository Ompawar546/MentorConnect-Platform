package com.mentorconnect.userservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mentorconnect.userservice.entity.MentorProfile;
	
public interface MentorProfileRepository extends JpaRepository<MentorProfile, Long> {

}
