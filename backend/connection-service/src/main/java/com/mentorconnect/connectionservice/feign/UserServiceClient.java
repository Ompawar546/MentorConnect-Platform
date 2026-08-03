package com.mentorconnect.connectionservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mentorconnect.connectionservice.dto.response.MentorPrivateResponse;
import com.mentorconnect.connectionservice.dto.response.StudentResponse;
import com.mentorconnect.connectionservice.dto.response.UserIdResponse;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceClient {

    @GetMapping("/internal/mentors/{mentorProfileId}/user-id")
    UserIdResponse getMentorUserId(@PathVariable Long mentorProfileId);

    @GetMapping("/internal/students/user/{userId}/private")
    StudentResponse getStudentByUserId(@PathVariable Long userId);

    @GetMapping("/internal/mentors/user/{userId}/private")
    MentorPrivateResponse getMentorByUserId(@PathVariable Long userId);
}