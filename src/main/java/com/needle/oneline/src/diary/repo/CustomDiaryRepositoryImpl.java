package com.needle.oneline.src.diary.repo;

import com.needle.oneline.src.common.BaseException;
import com.needle.oneline.src.diary.Diary;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.needle.oneline.src.diary.QDiary.diary;

@Repository
public class CustomDiaryRepositoryImpl implements CustomDiaryRepository{

    private final JPAQueryFactory jpaQueryFactory;
    public CustomDiaryRepositoryImpl(JPAQueryFactory jpaQueryFactory){
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<String> diaryContentByUserIdAndDate(LocalDate localDate, Long userId) throws BaseException {

        List<Diary> findDiary = diaryListFindByUserId(userId);
        List<String> findContent = findDiary.stream()
                .filter(d -> d.getDiaryDate().isEqual(localDate))
                .map(d -> d.getContents())
                .collect(Collectors.toList());

        return findContent;
    }

    @Override
    public boolean existDiaryByUserIdAndDate(Long userId, LocalDate localDate) throws BaseException {
        List<Diary> diary = diaryFindByUserIdAndDate(userId, localDate);
        if(diary.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public List<Diary> diaryFindByUserIdAndDate(Long userId, LocalDate diaryDate) throws BaseException {
        List<Diary> diaries = diaryListFindByUserId(userId).stream()
                .filter(d -> d.getDiaryDate().isEqual(diaryDate))
                .collect(Collectors.toList());;
        return diaries;
    }

    @Override
    public List<Diary> diaryListFindByUserId(Long userId) throws BaseException {
        List<Diary> diaryList = jpaQueryFactory
                .selectFrom(diary)
                .where(diary.user.id.eq(userId))
                .fetch();
        return diaryList;
    }
}
