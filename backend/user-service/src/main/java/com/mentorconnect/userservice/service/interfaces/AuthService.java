package com.mentorconnect.userservice.service.interfaces;

import com.mentorconnect.userservice.dto.request.LoginRequest;
import com.mentorconnect.userservice.dto.request.RegisterMentorRequest;
import com.mentorconnect.userservice.dto.request.RegisterStudentRequest;
import com.mentorconnect.userservice.dto.response.LoginResponse;
import com.mentorconnect.userservice.dto.response.RegisterResponse;

public interface AuthService {

    RegisterResponse registerStudent(RegisterStudentRequest request);

    RegisterResponse registerMentor(RegisterMentorRequest request);

    
    LoginResponse login(LoginRequest request);
}