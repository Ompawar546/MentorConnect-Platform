package com.mentorconnect.userservice.service.interfaces;

import java.util.List;

import com.mentorconnect.userservice.dto.request.UpdateMentorRequest;
import com.mentorconnect.userservice.dto.response.MentorCardResponse;
import com.mentorconnect.userservice.dto.response.MentorPrivateResponse;
import com.mentorconnect.userservice.dto.response.MentorPublicResponse;
import com.mentorconnect.userservice.dto.response.UserIdResponse;
import com.mentorconnect.userservice.enums.Skill;

public interface MentorService {

    MentorPrivateResponse getMyProfile();

    MentorPrivateResponse updateMyProfile(UpdateMentorRequest request);

    List<MentorCardResponse> getAllMentors();

    MentorPublicResponse getMentorById(Long mentorId);
    
    List<MentorCardResponse> filterMentors(
            Skill skill,
            String company,
            Boolean verified,
            Integer experienceYears);
    
    UserIdResponse getMentorUserId(Long mentorProfileId);

    MentorPrivateResponse getPrivateProfileByUserId(Long userId);
    
    

}