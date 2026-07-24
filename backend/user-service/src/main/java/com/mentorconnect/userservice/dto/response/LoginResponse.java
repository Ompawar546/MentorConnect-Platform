package com.mentorconnect.userservice.dto.response;

import com.mentorconnect.userservice.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private boolean success;

    private String message;

    private String token;

    private Long userId;

    private String username;

    private Role role;

}