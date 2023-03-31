package com.needle.oneline.src.diary;

import com.needle.oneline.src.user.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DiaryTest {

    @Test
    void 다이어리_생성테스트(){
        User user = new User();
        String content = "test";
        String length = "S";
        LocalDate diaryDate = LocalDate.now();
        Diary diary = new Diary(user,content,length,diaryDate);
        assertAll(
                () -> assertEquals(content, diary.getContents()),
                () -> assertEquals(length, diary.getLengthFlag()),
                () -> assertEquals(diaryDate, diary.getDiaryDate()),
                () -> assertEquals(user, diary.getUser())
        );
    }
}