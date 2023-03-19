package com.needle.oneline.src.common.auth.controller;

import com.needle.oneline.src.common.BaseResponse;
import com.needle.oneline.src.common.BaseResponseStatus;
import com.needle.oneline.src.common.auth.dto.request.AuthRequestDto;
import com.needle.oneline.src.common.auth.dto.response.AuthResponseDto;
import com.needle.oneline.src.common.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "로그인api",description = "access token과 sns의 종류를 보내줘야함! snsType은 enum타입으로 GOOGLE 또는 KAKAO" +
            "형태로 보내줘야함")
    public AuthResponseDto login(@RequestBody AuthRequestDto requestDto){
        return authService.snsLogin(requestDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse RuntimeEx(Exception e){
        log.info(e.getMessage());
        return new BaseResponse(BaseResponseStatus.SOCIAL_TOKEN_INVALID);
    }
}
