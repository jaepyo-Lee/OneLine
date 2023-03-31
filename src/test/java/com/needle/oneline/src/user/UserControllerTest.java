package com.needle.oneline.src.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Value("${app.auth.masterToken}")
    private String token;

    private static final String BASE_URL = "/users";
    @Test
    @DisplayName("유저id 반환 컨트롤러 테스트")
    void returnUserId() throws Exception{
        mockMvc.perform(get(BASE_URL + "/me")
                        .header("Authorization", "Bearer "+token)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(1)));//일단 저장되어있는 테스트데이터로 진행

    }
}