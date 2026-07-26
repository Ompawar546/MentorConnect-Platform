package com.mentorconnect.userservice.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
@Embeddable
public class Education {

    @Column(length = 100)
    private String degree;

    @Column(length = 100)
    private String branch;

    @Column(length = 150)
    private String college;

    private Integer passingYear;
}