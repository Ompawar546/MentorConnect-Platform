package com.mentorconnect.connectionservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mentorconnect.connectionservice.dto.response.ConnectionResponse;
import com.mentorconnect.connectionservice.dto.response.UserIdResponse;
import com.mentorconnect.connectionservice.entity.ConnectionRequest;
import com.mentorconnect.connectionservice.enums.ConnectionStatus;
import com.mentorconnect.connectionservice.exception.ResourceAlreadyExistsException;
import com.mentorconnect.connectionservice.feign.UserServiceClient;
import com.mentorconnect.connectionservice.repository.ConnectionRequestRepository;
import com.mentorconnect.connectionservice.service.interfaces.ConnectionService;
import com.mentorconnect.connectionservice.dto.response.StudentResponse;
import com.mentorconnect.connectionservice.dto.response.MentorPrivateResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConnectionServiceImpl implements ConnectionService {

    private final ConnectionRequestRepository repository;
    private final UserServiceClient userServiceClient;

    @Override
    public void sendRequest(Long mentorProfileId, Long studentId) {

        UserIdResponse response =
                userServiceClient.getMentorUserId(mentorProfileId);

        Long mentorUserId = response.getUserId();

        repository.findByStudentUserIdAndMentorUserIdAndStatus(
                studentId,
                mentorUserId,
                ConnectionStatus.PENDING)
                .ifPresent(r -> {
                    throw new ResourceAlreadyExistsException("Request already sent.");
                });

        ConnectionRequest request = ConnectionRequest.builder()
                .studentUserId(studentId)
                .mentorUserId(mentorUserId)
                .status(ConnectionStatus.PENDING)
                .requestedAt(LocalDateTime.now())
                .build();

        repository.save(request);
    }

    @Override
    public List<ConnectionResponse> getPendingRequests(Long mentorId) {

        return repository.findByMentorUserIdAndStatus(
                mentorId,
                ConnectionStatus.PENDING)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void acceptRequest(Long requestId) {

        ConnectionRequest request = repository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(ConnectionStatus.ACCEPTED);
        request.setRespondedAt(LocalDateTime.now());

        repository.save(request);
    }

    @Override
    public void rejectRequest(Long requestId) {

        ConnectionRequest request = repository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(ConnectionStatus.REJECTED);
        request.setRespondedAt(LocalDateTime.now());

        repository.save(request);
    }

    @Override
    public List<ConnectionResponse> getMyStudents(Long mentorId) {

        return repository.findByMentorUserIdAndStatus(
                mentorId,
                ConnectionStatus.ACCEPTED)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ConnectionResponse> getMyMentors(Long studentId) {

        return repository.findByStudentUserIdAndStatus(
                studentId,
                ConnectionStatus.ACCEPTED)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    
    
    
    
    private ConnectionResponse mapToResponse(ConnectionRequest request) {

        StudentResponse student =
                userServiceClient.getStudentByUserId(request.getStudentUserId());

        MentorPrivateResponse mentor =
                userServiceClient.getMentorByUserId(request.getMentorUserId());

        ConnectionResponse response = new ConnectionResponse();

        response.setId(request.getId());

        response.setStudentUserId(request.getStudentUserId());
        response.setMentorUserId(request.getMentorUserId());

        // Student Details
        response.setStudentName(
                student.getFirstName() + " " + student.getLastName());
        response.setStudentEmail(student.getEmail());
        response.setStudentProfilePicture(student.getProfilePictureUrl());

        // Mentor Details
        response.setMentorName(
                mentor.getFirstName() + " " + mentor.getLastName());
        response.setMentorEmail(mentor.getEmail());
        response.setMentorProfilePicture(mentor.getProfilePictureUrl());
        response.setMentorCompany(mentor.getCurrentCompany());
        response.setMentorDesignation(mentor.getCurrentDesignation());
        response.setMentorExperienceYears(mentor.getExperienceYears());

        response.setStatus(request.getStatus());
        response.setRequestedAt(request.getRequestedAt());
        response.setRespondedAt(request.getRespondedAt());

        return response;
    }

}