package com.mentorconnect.userservice.entity;

import java.util.ArrayList;
import java.util.List;

import com.mentorconnect.userservice.embeddable.Education;
import com.mentorconnect.userservice.embeddable.SocialLinks;
import com.mentorconnect.userservice.enums.AvailabilityStatus;
import com.mentorconnect.userservice.enums.Skill;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "mentor_profiles")
public class MentorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    // ===========================
    // Professional Information
    // ===========================

    @Column(length = 100)
    private String currentCompany;

    @ElementCollection
    @CollectionTable(
            name = "mentor_previous_companies",
            joinColumns = @JoinColumn(name = "mentor_profile_id"))
    @Column(name = "company_name", length = 100)
    @Size(max = 2, message = "Maximum 2 previous companies are allowed.")
    @Default
    private List<String> previousCompanies = new ArrayList<>();

    @Column(length = 100)
    private String currentDesignation;

    @Max(value = 100, message = "Experience cannot exceed 100 years.")
    private Integer experienceYears;

    // ===========================
    // Education
    // ===========================

    @Embedded
    private Education education;

    // ===========================
    // Skills
    // ===========================

    @ElementCollection(targetClass = Skill.class)
    @CollectionTable(
            name = "mentor_skills",
            joinColumns = @JoinColumn(name = "mentor_profile_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "skill")
    @Size(max = 6, message = "Maximum 6 skills are allowed.")
    @Default
    private List<Skill> skills = new ArrayList<>();

    // ===========================
    // Other Information
    // ===========================

    @Column(length = 200)
    private String expertise;

    @Column(length = 1000)
    private String bio;

    @Column(length = 255)
    private String resumeFileUrl;

    @Column(length = 255)
    private String employmentProofFileUrl;

    @Embedded
    private SocialLinks socialLinks;

    // ===========================
    // Profile Status
    // ===========================

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private AvailabilityStatus availabilityStatus =
            AvailabilityStatus.AVAILABLE;

    @Builder.Default
    private Double averageRating = 0.0;

    @Builder.Default
    private Boolean verified = false;
}