package com.needle.oneline.src.common.auth.client;

import com.needle.oneline.src.common.auth.dto.response.KakaoUserResponseDto;
import com.needle.oneline.src.common.auth.exception.TokenValidFailedException;
import com.needle.oneline.src.common.enumerate.RoleType;
import com.needle.oneline.src.common.enumerate.SnsType;
import com.needle.oneline.src.user.User;
import lombok.RequiredArgsConstructor;
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
        KakaoUserResponseDto kakaoUserResponseDto = webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .headers(h -> h.setBearerAuth(accessToken))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new TokenValidFailedException("Social Access Token is unauthorized")))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new TokenValidFailedException("Internal Server Error")))
                .bodyToMono(KakaoUserResponseDto.class)
                .block();
        return User.builder()
                .name(kakaoUserResponseDto.getName())
                .picture(kakaoUserResponseDto.getPicture()!=null ? kakaoUserResponseDto.getPicture() : "")
                .socialId(kakaoUserResponseDto.getSub())
                .email(kakaoUserResponseDto.getEmail())
                .roleType(RoleType.USER)
                .snsType(SnsType.KAKAO)
                .build();
    }
}
