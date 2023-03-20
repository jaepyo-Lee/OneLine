package com.needle.oneline.src.common.auth.client;

import com.needle.oneline.src.common.auth.dto.response.KakaoUserResponseDto;
import com.needle.oneline.src.common.auth.exception.TokenValidFailedException;
import com.needle.oneline.src.common.enumerate.RoleType;
import com.needle.oneline.src.common.enumerate.SnsType;
import com.needle.oneline.src.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class KakaoClient implements ClientProxy{
    private final WebClient webClient;
    public KakaoClient(){
        this.webClient = WebClient.create();
    }
    @Override
    public User getUserData(String accessToken) {
        KakaoUserResponseDto kakaoUserResponseDto = webClient.post()
                .uri("https://kapi.kakao.com/v2/user/me")
                .headers(h -> h.setBearerAuth(accessToken))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new TokenValidFailedException("Social Access Token is unauthorized")))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new TokenValidFailedException("Internal Server Error")))
                .bodyToMono(KakaoUserResponseDto.class)
                .block();
        return User.builder()
                .name(kakaoUserResponseDto.getProperties().getNickname())
                .picture(kakaoUserResponseDto.getProperties().getProfileImage()!=null ? kakaoUserResponseDto.getProperties().getProfileImage() : "")
                .socialId(kakaoUserResponseDto.getId().toString())
                .email(kakaoUserResponseDto.getKakao_account().getEmail())
                .roleType(RoleType.USER)
                .snsType(SnsType.KAKAO)
                .build();
    }
}
