package com.needle.oneline.src.diary.repo;

import com.needle.oneline.config.TestQuerydslConfig;
import com.needle.oneline.src.common.enumerate.RoleType;
import com.needle.oneline.src.diary.Diary;
import com.needle.oneline.src.user.User;
import com.needle.oneline.src.user.repo.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.List;

import static com.needle.oneline.src.common.enumerate.SnsType.KAKAO;
import static com.needle.oneline.src.diary.QDiary.diary;
import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({TestQuerydslConfig.class,CustomDiaryRepositoryImpl.class})
class CustomDiaryRepositoryImplTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomDiaryRepositoryImpl customDiaryRepo;

    @Test
    void diaryContentByUserIdAndDate() {
    }

    @Test
    void existDiaryByUserIdAndDate() {
    }

    @Test
    @DisplayName("유저id와 날짜로 게시글 조회테스트")
    void diaryFindByUserIdAndDate() throws Exception{
        User saveUser = userRepository.save(userBuilder());
        Diary saveDiary = diaryRepository.save(diaryBuilder(saveUser));
        List<Diary> diaries = customDiaryRepo.diaryFindByUserIdAndDate(saveUser.getId(), now());
        org.assertj.core.api.Assertions.assertThat(diaries.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("유저id로 게시글 조회테스트")
    void diaryListFindByUserId() {
        User saveUser = userRepository.save(userBuilder());
        Diary saveDiary = diaryRepository.save(diaryBuilder(saveUser));
        List<Diary> diaryList = jpaQueryFactory
                .selectFrom(diary)
                .where(diary.user.id.eq(saveUser.getId()))
                .fetch();
        assertThat(diaryList.size()).isEqualTo(1);
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