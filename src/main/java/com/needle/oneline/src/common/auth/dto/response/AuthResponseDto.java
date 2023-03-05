package com.needle.oneline.src.common.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthResponseDto {
    private String accessToken;
    private boolean isNewMember;
}
