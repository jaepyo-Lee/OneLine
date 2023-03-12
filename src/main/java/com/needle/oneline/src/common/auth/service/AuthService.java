package com.needle.oneline.src.common.auth.service;

import com.needle.oneline.src.common.auth.client.GoogleClient;
import com.needle.oneline.src.common.auth.client.KakaoClient;
import com.needle.oneline.src.common.auth.dto.AuthToken;
import com.needle.oneline.src.common.auth.dto.request.AuthRequestDto;
import com.needle.oneline.src.common.auth.dto.response.AuthResponseDto;
import com.needle.oneline.src.common.auth.jwt.TokenProvider;
import com.needle.oneline.src.common.enumerate.SnsType;
import com.needle.oneline.src.user.User;
import com.needle.oneline.src.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final GoogleClient googleClient;
    private final KakaoClient kakaoClient;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    public AuthResponseDto snsLogin(AuthRequestDto requestDto){
        User user = getUserDataBySns(requestDto);
        String socialId = user.getSocialId();
        User userBySocialId = userRepository.findBySocialId(socialId);
        AuthToken userAppToken = tokenProvider.createUserAppToken(socialId);
        if(userBySocialId!=null){
            userRepository.save(user);
            return AuthResponseDto.builder()
                    .accessToken(userAppToken.getAccessToken())
                    .isNewMember(true)
                    .build(); //첫 로그인
        }
        return AuthResponseDto.builder()
                .accessToken(userAppToken.getAccessToken())
                .isNewMember(false)
                .build(); //다시 로그인
    }

    public User getUserDataBySns(AuthRequestDto requestDto){
        switch (requestDto.getSnsType()){
            case "GOOGLE":
                return googleClient.getUserData(requestDto.getAuthToken());
            case "KAKAO":
                return kakaoClient.getUserData(requestDto.getAuthToken());
            default:
                throw new IllegalArgumentException("Invalid Request");
        }
    }
}
