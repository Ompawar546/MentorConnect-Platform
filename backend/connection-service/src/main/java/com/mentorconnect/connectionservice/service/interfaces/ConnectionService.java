package com.mentorconnect.connectionservice.service.interfaces;

import java.util.List;

import com.mentorconnect.connectionservice.entity.ConnectionRequest;

public interface ConnectionService {

    void sendRequest(Long mentorProfileId, Long studentId);

    List<ConnectionRequest> getPendingRequests(Long mentorId);

    void acceptRequest(Long requestId);

    void rejectRequest(Long requestId);

    List<ConnectionRequest> getMyStudents(Long mentorId);

    List<ConnectionRequest> getMyMentors(Long studentId);

}