package com.mentorconnect.userservice.mapper;

import org.springframework.stereotype.Component;

import com.mentorconnect.userservice.dto.response.StudentResponse;
import com.mentorconnect.userservice.entity.StudentProfile;

@Component
public class StudentMapper {

    public StudentResponse toResponse(StudentProfile student) {

        return StudentResponse.builder()

                // User Details
                .userId(student.getUser().getId())
                .username(student.getUser().getUsername())
                .firstName(student.getUser().getFirstName())
                .lastName(student.getUser().getLastName())
                .email(student.getUser().getEmail())
                .phone(student.getUser().getPhone())
                .profilePictureUrl(student.getUser().getProfilePictureUrl())

                // Student Details
                .college(student.getCollege())
                .degree(student.getDegree())
                .branch(student.getBranch())
                .graduationYear(student.getGraduationYear())
                .semester(student.getSemester())
                .cgpa(student.getCgpa())
                .bio(student.getBio())
                .socialLinks(student.getSocialLinks())
                .resumeUrl(student.getResumeUrl())

                .build();
    }

}