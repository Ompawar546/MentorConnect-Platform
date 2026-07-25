package com.mentorconnect.userservice.service.impl;

import org.springframework.stereotype.Service;

import com.mentorconnect.userservice.dto.request.UpdateStudentRequest;
import com.mentorconnect.userservice.dto.response.StudentResponse;
import com.mentorconnect.userservice.entity.StudentProfile;
import com.mentorconnect.userservice.entity.User;
import com.mentorconnect.userservice.exception.ResourceNotFoundException;
import com.mentorconnect.userservice.mapper.StudentMapper;
import com.mentorconnect.userservice.repository.StudentProfileRepository;
import com.mentorconnect.userservice.repository.UserRepository;
import com.mentorconnect.userservice.service.interfaces.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentProfileRepository studentProfileRepository;
    private final UserRepository userRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponse getStudentById(Long userId) {

        StudentProfile student = studentProfileRepository
                .findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        return studentMapper.toResponse(student);
    }

    @Override
    public StudentResponse getCurrentStudent(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        StudentProfile student = studentProfileRepository
                .findByUserId(user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student profile not found"));

        return studentMapper.toResponse(student);
    }

    @Override
    public StudentResponse updateCurrentStudent(String email,
                                                UpdateStudentRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        StudentProfile student = studentProfileRepository
                .findByUserId(user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student profile not found"));

        // Update User Details
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setProfilePictureUrl(request.getProfilePictureUrl());

        userRepository.save(user);

        // Update Student Profile
        student.setCollege(request.getCollege());
        student.setDegree(request.getDegree());
        student.setBranch(request.getBranch());
        student.setGraduationYear(request.getGraduationYear());
        student.setSemester(request.getSemester());
        student.setCgpa(request.getCgpa());
        student.setBio(request.getBio());
        student.setSocialLinks(request.getSocialLinks());
        student.setResumeUrl(request.getResumeUrl());

        studentProfileRepository.save(student);

        return studentMapper.toResponse(student);
    }
}