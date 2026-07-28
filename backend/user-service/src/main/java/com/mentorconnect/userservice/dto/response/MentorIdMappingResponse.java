package com.mentorconnect.userservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MentorIdMappingResponse {

    private Long mentorProfileId;
    private Long mentorUserId;

}