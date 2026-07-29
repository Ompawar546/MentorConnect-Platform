package com.mentorconnect.connectionservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mentorconnect.connectionservice.entity.ConnectionRequest;
import com.mentorconnect.connectionservice.service.interfaces.ConnectionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/connections")
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionService connectionService;

    @PostMapping("/request/{mentorProfileId}")
    public ResponseEntity<String> sendRequest(
            @PathVariable Long mentorProfileId,
            @RequestHeader("X-User-Id") Long studentId,
            @RequestHeader("X-User-Role") String role) {

        if (!"STUDENT".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Only students can send connection requests.");
        }

        connectionService.sendRequest(mentorProfileId, studentId);

        return ResponseEntity.ok("Connection request sent successfully.");
    }

    @GetMapping("/pending")
    public ResponseEntity<?> getPendingRequests(
            @RequestHeader("X-User-Id") Long mentorId,
            @RequestHeader("X-User-Role") String role) {

        if (!"MENTOR".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Only mentors can view pending requests.");
        }

        return ResponseEntity.ok(connectionService.getPendingRequests(mentorId));
    }

    @PutMapping("/{requestId}/accept")
    public ResponseEntity<String> acceptRequest(
            @PathVariable Long requestId,
            @RequestHeader("X-User-Role") String role) {

        if (!"MENTOR".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Only mentors can accept requests.");
        }

        connectionService.acceptRequest(requestId);

        return ResponseEntity.ok("Request accepted.");
    }

    @PutMapping("/{requestId}/reject")
    public ResponseEntity<String> rejectRequest(
            @PathVariable Long requestId,
            @RequestHeader("X-User-Role") String role) {

        if (!"MENTOR".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Only mentors can reject requests.");
        }

        connectionService.rejectRequest(requestId);

        return ResponseEntity.ok("Request rejected.");
    }

    @GetMapping("/my-students")
    public ResponseEntity<?> getMyStudents(
            @RequestHeader("X-User-Id") Long mentorId,
            @RequestHeader("X-User-Role") String role) {

        if (!"MENTOR".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Only mentors can view students.");
        }

        return ResponseEntity.ok(connectionService.getMyStudents(mentorId));
    }

    @GetMapping("/my-mentors")
    public ResponseEntity<?> getMyMentors(
            @RequestHeader("X-User-Id") Long studentId,
            @RequestHeader("X-User-Role") String role) {

        if (!"STUDENT".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Only students can view mentors.");
        }

        return ResponseEntity.ok(connectionService.getMyMentors(studentId));
    }
}