package com.mentorconnect.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mentorconnect.userservice.dto.response.MentorPrivateResponse;
import com.mentorconnect.userservice.dto.response.UserIdResponse;
import com.mentorconnect.userservice.service.interfaces.MentorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/internal/mentors")
@RequiredArgsConstructor
public class InternalMentorController {

    private final MentorService mentorService;

    @GetMapping("/{mentorProfileId}/user-id")
    public ResponseEntity<UserIdResponse> getMentorUserId(
            @PathVariable Long mentorProfileId) {

        return ResponseEntity.ok(
                mentorService.getMentorUserId(mentorProfileId));
    }

    @GetMapping("/user/{userId}/private")
    public ResponseEntity<MentorPrivateResponse> getPrivateProfile(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                mentorService.getPrivateProfileByUserId(userId));
    }
}