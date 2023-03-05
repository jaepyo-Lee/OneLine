package com.needle.oneline.src.common.auth.controller;

import com.needle.oneline.src.common.auth.dto.request.AuthRequestDto;
import com.needle.oneline.src.common.auth.dto.response.AuthResponseDto;
import com.needle.oneline.src.common.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "로그인api",description = "access token과 sns의 종류를 보내줘야함! snsType은 enum타입으로 GOOGLE 또는 NAVER" +
            "형태로 보내줘야함")
    public AuthResponseDto login(@RequestBody AuthRequestDto requestDto){
        return authService.snsLogin(requestDto);
    }
}
