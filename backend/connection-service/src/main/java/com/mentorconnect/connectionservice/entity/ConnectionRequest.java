package com.mentorconnect.connectionservice.entity;

import java.time.LocalDateTime;

import com.mentorconnect.connectionservice.enums.ConnectionStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "connection_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConnectionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentUserId;

    private Long mentorUserId;

    @Enumerated(EnumType.STRING)
    private ConnectionStatus status;

    private LocalDateTime requestedAt;

    private LocalDateTime respondedAt;
}