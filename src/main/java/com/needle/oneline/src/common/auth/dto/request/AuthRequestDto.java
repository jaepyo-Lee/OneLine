package com.needle.oneline.src.common.auth.dto.request;

import com.needle.oneline.src.common.enumerate.SnsType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDto {
    private String authToken; //1회성 토큰
    private SnsType snsType;
}
