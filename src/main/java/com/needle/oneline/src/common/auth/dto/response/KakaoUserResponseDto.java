package com.needle.oneline.src.common.auth.dto.response;

import lombok.Getter;

@Getter
public class KakaoUserResponseDto {
    private String sub;
    private String name;
    private String picture;
    private String email;
}
