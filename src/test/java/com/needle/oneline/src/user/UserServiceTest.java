package com.needle.oneline.src.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Value("${app.auth.masterToken}")
    private String Bearertoken;
    @Autowired
    private UserService userService;

    @Autowired
    private MockHttpServletRequest request;
    @Test
    @DisplayName("토큰을 통한 유저 조회테스트(성공)")
    void findByToken(){
        request.addHeader("Authorization","Bearer "+Bearertoken);
        Long userId = userService.findUserId(request);
        org.assertj.core.api.Assertions.assertThat(userId).isEqualTo(1);//일단 db에 저장되어있는것 수동으로 표현
    }
}