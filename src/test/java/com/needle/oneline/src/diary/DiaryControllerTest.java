package com.needle.oneline.src.diary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.needle.oneline.src.common.enumerate.RoleType;
import com.needle.oneline.src.diary.dto.request.FindContentsRequestDto;
import com.needle.oneline.src.diary.dto.request.SaveContentsRequestDto;
import com.needle.oneline.src.diary.repo.DiaryRepository;
import com.needle.oneline.src.user.User;
import com.needle.oneline.src.user.repo.UserRepository;
import org.junit.jupiter.api.DisplayName;
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

import static com.needle.oneline.src.common.enumerate.SnsType.KAKAO;
import static java.time.LocalDate.now;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class DiaryControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Autowired
    DiaryService diaryService;

    @Value("${app.auth.testToken}")
    private String token;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DiaryRepository diaryRepository;

    private static final String BASE_URL = "/users";

    @Test
    @DisplayName("일기 내용조회하기(유저가 존재하는 경우)")
    void findDiaryExistUser() throws Exception {
        User saveUser = userRepository.save(userBuilder());
        //when
        LocalDate diaryDate = LocalDate.of(2020, 02, 02);
        SaveContentsRequestDto saveContentsRequestDto = new SaveContentsRequestDto("test",diaryDate,"S");
        diaryService.saveContents(saveUser.getId(), saveContentsRequestDto);
        //given
        HashMap<String, LocalDate> query = new HashMap<>();
        query.put("diaryDate", diaryDate);
        //then
        mockMvc.perform(get(BASE_URL + "/" + saveUser.getId() + "/diary/content")
                        .header("Authorization", "Bearer " + token)
                        .queryParam("diaryDate", "2020-02-02")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.result.content[0]",is(saveContentsRequestDto.getContents())));
    }

    @Test
    void existDiary() {

    }


    @Test
    void modifiedDiary() {
    }

    private static User userBuilder() {
        User user = User.builder()
                .email("test")
                .snsType(KAKAO)
                .picture("test")
                .roleType(RoleType.USER)
                .name("test")
                .socialId("test")
                .build();
        return user;
    }

    private static Diary diaryBuilder(User user){
        String content = "test";
        String length = "S";
        LocalDate diaryDate = now();
        return Diary.builder()
                .contents(content)
                .diaryDate(diaryDate)
                .lengthFlag(length)
                .user(user)
                .build();
    }
}