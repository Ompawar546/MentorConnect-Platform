package com.mentorconnect.userservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mentorconnect.userservice.dto.request.UpdateMentorRequest;
import com.mentorconnect.userservice.dto.response.MentorCardResponse;
import com.mentorconnect.userservice.dto.response.MentorPrivateResponse;
import com.mentorconnect.userservice.dto.response.MentorPublicResponse;
import com.mentorconnect.userservice.enums.Skill;
import com.mentorconnect.userservice.service.interfaces.MentorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/mentors")
@RequiredArgsConstructor
public class MentorController {

    private final MentorService mentorService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('MENTOR')")
    public ResponseEntity<MentorPrivateResponse> getMyProfile() {

        return ResponseEntity.ok(mentorService.getMyProfile());
    }

    @PutMapping("/me")
    @PreAuthorize("hasRole('MENTOR')")
    public ResponseEntity<MentorPrivateResponse> updateMyProfile(
            @Valid @RequestBody UpdateMentorRequest request) {

        return ResponseEntity.ok(mentorService.updateMyProfile(request));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('STUDENT','MENTOR')")
    public ResponseEntity<List<MentorCardResponse>> getAllMentors() {

        return ResponseEntity.ok(mentorService.getAllMentors());
    }

    @GetMapping("/{mentorId}")
    @PreAuthorize("hasAnyRole('STUDENT','MENTOR')")
    public ResponseEntity<MentorPublicResponse> getMentorById(
            @PathVariable Long mentorId) {

        return ResponseEntity.ok(mentorService.getMentorById(mentorId));
    }
    
    
    
    @GetMapping("/search")
    public ResponseEntity<List<MentorCardResponse>> filterMentors(

            @RequestParam(required = false) Skill skill,

            @RequestParam(required = false) String company,

            @RequestParam(required = false) Boolean verified,

            @RequestParam(required = false) Integer experienceYears) {

        return ResponseEntity.ok(
                mentorService.filterMentors(
                        skill,
                        company,
                        verified,
                        experienceYears));
    }
}