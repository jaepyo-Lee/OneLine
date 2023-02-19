package com.needle.oneline.src.diary;

import com.needle.oneline.src.diary.repo.CustomDiaryRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class DiaryServiceTest {
    DiaryService diaryService;
    @MockBean
    CustomDiaryRepositoryImpl customDiaryRepository;
    @BeforeEach
    public void setup(){
        this.diaryService = new DiaryService(customDiaryRepository);
    }
    @Test
    void findContents() {

    }
}