package com.mentorconnect.userservice.entity;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.mentorconnect.userservice.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "users")
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	 @Column(nullable = false, unique = true, length = 50)
	    private String username;

	    @Column(nullable = false, length = 50)
	    private String firstName;

	    @Column(nullable = false, length = 50)
	    private String lastName;

	    @Column(nullable = false, unique = true, length = 100)
	    private String email;

	    @Column(nullable = false)
	    private String password;

	    @Column(unique = true, length = 15)
	    private String phone;

	    @Column(length = 255)
	    private String profilePictureUrl;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private Role role;

	    @Column(nullable = false)
	    @Builder.Default
	    private boolean emailVerified = false;

	    @Column(nullable = false)
	    @Builder.Default
	    private boolean active = true;

	    @CreationTimestamp
	    @Column(updatable = false)
	    private LocalDateTime createdAt;

	    @UpdateTimestamp
	    private LocalDateTime updatedAt;

	    @OneToOne(mappedBy = "user")
	    private StudentProfile studentProfile;

	    @OneToOne(mappedBy = "user")
	    private MentorProfile mentorProfile;
	
	
	
	
}
