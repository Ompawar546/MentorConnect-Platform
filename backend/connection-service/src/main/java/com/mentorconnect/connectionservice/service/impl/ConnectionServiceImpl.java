package com.mentorconnect.connectionservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mentorconnect.connectionservice.dto.response.UserIdResponse;
import com.mentorconnect.connectionservice.entity.ConnectionRequest;
import com.mentorconnect.connectionservice.enums.ConnectionStatus;
import com.mentorconnect.connectionservice.feign.UserServiceClient;
import com.mentorconnect.connectionservice.repository.ConnectionRequestRepository;
import com.mentorconnect.connectionservice.service.interfaces.ConnectionService;

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
                    throw new RuntimeException("Request already sent.");
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
    public List<ConnectionRequest> getPendingRequests(Long mentorId) {

        return repository.findByMentorUserIdAndStatus(
                mentorId,
                ConnectionStatus.PENDING);
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
    public List<ConnectionRequest> getMyStudents(Long mentorId) {

        return repository.findByMentorUserIdAndStatus(
                mentorId,
                ConnectionStatus.ACCEPTED);
    }
    
    @Override
    public List<ConnectionRequest> getMyMentors(Long studentId) {

        return repository.findByStudentUserIdAndStatus(
                studentId,
                ConnectionStatus.ACCEPTED);
    }
    
    
}