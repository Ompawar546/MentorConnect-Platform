package com.mentorconnect.userservice.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mentorconnect.userservice.dto.request.LoginRequest;
import com.mentorconnect.userservice.dto.request.RegisterMentorRequest;
import com.mentorconnect.userservice.dto.request.RegisterStudentRequest;
import com.mentorconnect.userservice.dto.response.LoginResponse;
import com.mentorconnect.userservice.dto.response.RegisterResponse;
import com.mentorconnect.userservice.entity.MentorProfile;
import com.mentorconnect.userservice.entity.StudentProfile;
import com.mentorconnect.userservice.entity.User;
import com.mentorconnect.userservice.enums.Role;
import com.mentorconnect.userservice.repository.MentorProfileRepository;
import com.mentorconnect.userservice.repository.StudentProfileRepository;
import com.mentorconnect.userservice.repository.UserRepository;
import com.mentorconnect.userservice.service.interfaces.AuthService;
import com.mentorconnect.userservice.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final StudentProfileRepository studentProfileRepository;

    private final MentorProfileRepository mentorProfileRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    
    @Transactional
    @Override
    public RegisterResponse registerStudent(RegisterStudentRequest request) {

        // Check Email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists.");
        }

        // Check Username
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists.");
        }

        // Create User
        User user = new User();

        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());

        user.setRole(Role.STUDENT);
        user.setActive(true);
        user.setEmailVerified(false);

        // Save User
        User savedUser = userRepository.save(user);

        // Create Student Profile
        StudentProfile studentProfile = new StudentProfile();

        studentProfile.setUser(savedUser);
        studentProfile.setCollege(request.getCollege());
        studentProfile.setDegree(request.getDegree());
        studentProfile.setBranch(request.getBranch());
        studentProfile.setGraduationYear(request.getGraduationYear());
        studentProfile.setSemester(request.getSemester());
        studentProfile.setCgpa(request.getCgpa());
        studentProfile.setBio(request.getBio());
        studentProfile.setSocialLinks(request.getSocialLinks());
        studentProfile.setResumeUrl(request.getResumeUrl());

        // Save Student Profile
        studentProfileRepository.save(studentProfile);

        // Return Response
        return RegisterResponse.builder()
                .success(true)
                .message("Student Registered Successfully")
                .userId(savedUser.getId())
                .username(savedUser.getUsername())
                .role(savedUser.getRole())
                .build();
    }
    
    @Transactional
    @Override
    public RegisterResponse registerMentor(RegisterMentorRequest request) {

        // Check Email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists.");
        }

        // Check Username
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists.");
        }

        // Create User
        User user = new User();

        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setPhone(request.getPhone());

        user.setRole(Role.MENTOR);

        user.setActive(true);

        user.setEmailVerified(false);

     // Save User
        User savedUser = userRepository.save(user);

        // Create Empty Mentor Profile
        MentorProfile mentorProfile = new MentorProfile();
        mentorProfile.setUser(savedUser);

        // Save Mentor Profile
        mentorProfileRepository.save(mentorProfile);

        // Return Response
        return RegisterResponse.builder()
                .success(true)
                .message("Mentor Registered Successfully")
                .userId(savedUser.getId())
                .username(savedUser.getUsername())
                .role(savedUser.getRole())
                .build();

    }
    
    
    
    
    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user);

        return LoginResponse.builder()
                .success(true)
                .message("Login Successful")
                .token(token)
                .userId(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
    
    
    
    
    
}