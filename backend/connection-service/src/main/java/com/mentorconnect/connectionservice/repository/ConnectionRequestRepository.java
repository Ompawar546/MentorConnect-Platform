package com.mentorconnect.connectionservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mentorconnect.connectionservice.entity.ConnectionRequest;
import com.mentorconnect.connectionservice.enums.ConnectionStatus;

public interface ConnectionRequestRepository
        extends JpaRepository<ConnectionRequest, Long> {

    Optional<ConnectionRequest> findByStudentUserIdAndMentorUserIdAndStatus(
            Long studentUserId,
            Long mentorUserId,
            ConnectionStatus status);

    

    List<ConnectionRequest> findByStudentUserIdAndStatus(
            Long studentUserId,
            ConnectionStatus status);
    
    
    List<ConnectionRequest> findByMentorUserIdAndStatus(
            Long mentorUserId,
            ConnectionStatus status);
}