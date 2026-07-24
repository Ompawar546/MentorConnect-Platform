package com.mentorconnect.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Embedded;
import com.mentorconnect.userservice.embeddable.SocialLinks;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "student_profiles")
public class StudentProfile {
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private String college;

    @Column(nullable = false)
    private String degree;

    @Column(nullable = false)
    private String branch;

    @Column(nullable = false)
    private Integer graduationYear;

    private Integer semester;

    private Double cgpa;

    @Column(length = 500)
    private String bio;

    @Embedded
    private SocialLinks socialLinks;

    private String resumeUrl;
	
	
}
