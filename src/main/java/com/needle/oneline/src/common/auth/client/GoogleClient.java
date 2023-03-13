package com.needle.oneline.src.common.auth.client;

import com.needle.oneline.src.common.auth.dto.response.GoogleUserResponse;
import com.needle.oneline.src.common.enumerate.RoleType;
import com.needle.oneline.src.common.enumerate.SnsType;
import com.needle.oneline.src.common.auth.exception.TokenValidFailedException;
import com.needle.oneline.src.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GoogleClient implements ClientProxy{
    private final WebClient webClient;

    public GoogleClient() {
        this.webClient = WebClient.create();
    }

    @Override
    public User getUserData(String accessToken) {
        GoogleUserResponse googleUserResponse = webClient.get()
                .uri("https://oauth2.googleapis.com/tokeninfo", builder -> builder.queryParam("id_token", accessToken).build())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new TokenValidFailedException("Social Access Token is unauthorized by jaepyo")))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new TokenValidFailedException("Internal Server Error by jaepyo")))
                .bodyToMono(GoogleUserResponse.class)
                .block();
        return User.builder()
                .socialId(googleUserResponse.getSub())
                .name(googleUserResponse.getName())
                .email(googleUserResponse.getEmail())
                .snsType(SnsType.GOOGLE)
                .roleType(RoleType.USER)
                .picture(googleUserResponse.getPicture()!=null ? googleUserResponse.getPicture() : "")
                .build();
    }
}
