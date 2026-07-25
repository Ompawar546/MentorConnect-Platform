package com.mentorconnect.userservice.service.interfaces;

import com.mentorconnect.userservice.dto.request.UpdateStudentRequest;
import com.mentorconnect.userservice.dto.response.StudentResponse;

public interface StudentService {

    StudentResponse getStudentById(Long userId);

    StudentResponse getCurrentStudent(String email);

    StudentResponse updateCurrentStudent(String email, UpdateStudentRequest request);

}