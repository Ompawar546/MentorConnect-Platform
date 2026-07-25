package com.mentorconnect.userservice.dto.request;

import com.mentorconnect.userservice.embeddable.SocialLinks;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentRequest {

    // User Details

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String lastName;

    @Pattern(
        regexp = "^[0-9]{10}$",
        message = "Phone number must contain exactly 10 digits"
    )
    private String phone;

    @Size(max = 255, message = "Profile picture URL is too long")
    private String profilePictureUrl;

    // Student Details

    @NotBlank(message = "College is required")
    @Size(max = 150)
    private String college;

    @NotBlank(message = "Degree is required")
    @Size(max = 100)
    private String degree;

    @NotBlank(message = "Branch is required")
    @Size(max = 100)
    private String branch;

    @Min(value = 2000, message = "Invalid graduation year")
    @Max(value = 2100, message = "Invalid graduation year")
    private Integer graduationYear;

    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 12, message = "Semester cannot exceed 12")
    private Integer semester;

    @DecimalMin(value = "0.0", message = "CGPA cannot be negative")
    @DecimalMax(value = "10.0", message = "CGPA cannot exceed 10")
    private Double cgpa;

    @Size(max = 1000)
    private String bio;

    private SocialLinks socialLinks;

    @Size(max = 255)
    private String resumeUrl;
}