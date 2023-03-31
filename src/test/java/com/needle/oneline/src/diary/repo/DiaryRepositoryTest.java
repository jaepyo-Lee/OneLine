package com.needle.oneline.src.diary.repo;

import com.needle.oneline.src.diary.Diary;
import com.needle.oneline.src.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
class DiaryRepositoryTest {
    @Autowired
    private DiaryRepository diaryRepository;

    @Test
    void 다이어리_저장(){
        //given
        Diary diary = diaryBuilder();
        //when
        Diary save = diaryRepository.save(diary);
        //then
        Assertions.assertThat(save.getContents()).isEqualTo(diary.getContents());
    }

    @Test
    void findDiary() throws Exception{
        //given
        Diary diary = diaryBuilder();
        //when
        Diary save = diaryRepository.save(diary);
        Optional<Diary> byId = diaryRepository.findById(save.getId());
        //then
        Assertions.assertThat(byId.get().getContents()).isEqualTo(diary.getContents());
    }
    private static Diary diaryBuilder(){
        User user = new User();
        String content = "test";
        String length = "S";
        LocalDate diaryDate = LocalDate.now();
        return Diary.builder()
                .contents(content)
                .diaryDate(diaryDate)
                .lengthFlag(length)
                .user(user)
                .build();
    }
}