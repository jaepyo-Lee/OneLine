package com.needle.oneline.src.diary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.needle.oneline.src.diary.dto.request.FindContentsRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class DiaryControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Value("${app.auth.testToken}")
    private String token;

    private static final String BASE_URL = "/users";

    @Test
    void findExistDiary() throws Exception {
        //when
        LocalDate diaryDate = LocalDate.of(2020, 2, 2);
        //given
        HashMap<String, LocalDate> query = new HashMap<>();
        query.put("diaryDate", diaryDate);
        //then
        mockMvc.perform(get(BASE_URL + "/1/diary/content")
                        .header("Authorization", "Bearer " + token)
                        .queryParam("diaryDate","2020-02-02")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void existDiary() {
    }

    @Test
    void saveDiary() {
    }

    @Test
    void modifiedDiary() {
    }
}