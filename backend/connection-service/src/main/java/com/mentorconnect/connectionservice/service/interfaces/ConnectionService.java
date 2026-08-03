package com.mentorconnect.connectionservice.service.interfaces;

import java.util.List;

import com.mentorconnect.connectionservice.dto.response.ConnectionResponse;

public interface ConnectionService {

    void sendRequest(Long mentorProfileId, Long studentId);

    List<ConnectionResponse> getPendingRequests(Long mentorId);

    void acceptRequest(Long requestId);

    void rejectRequest(Long requestId);

    List<ConnectionResponse> getMyStudents(Long mentorId);

    List<ConnectionResponse> getMyMentors(Long studentId);

}