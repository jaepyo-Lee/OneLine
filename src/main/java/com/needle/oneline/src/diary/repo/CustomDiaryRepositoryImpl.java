package com.needle.oneline.src.diary.repo;

import com.needle.oneline.src.common.BaseException;
import com.needle.oneline.src.common.BaseResponseStatus;
import com.needle.oneline.src.diary.Diary;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.needle.oneline.src.common.BaseResponseStatus.USER_NOT_FOUND;
import static com.needle.oneline.src.common.BaseResponseStatus.USER_NOT_HAVING_DIARY;
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
                .filter(d -> d.getCreatedDate().toLocalDate().isEqual(localDate))
                .map(d -> d.getContents())
                .collect(Collectors.toList());

        return findContent;
    }

    @Override
    public boolean existDiaryByUserIdAndDate(Long userId, LocalDate localDate) throws BaseException {
        Diary diary = diaryFindByUserIdAndDate(userId, localDate);
        if(diary==null) {
            return false;
        }
        return true;
    }

    @Override
    public Diary diaryFindByUserIdAndDate(Long userId, LocalDate localDate) throws BaseException {
        List<Diary> diaries = diaryListFindByUserId(userId);
        List<Diary> collect = diaries.stream()
                .filter(d -> d.getDiaryDate().isEqual(localDate))
                .collect(Collectors.toList());
        return collect.get(0);
    }

    @Override
    public List<Diary> diaryListFindByUserId(Long userId) throws BaseException {
        List<Diary> diaryList = jpaQueryFactory
                .selectFrom(diary)
                .where(diary.user.id.eq(userId))
                .fetch();
        if(diaryList.isEmpty()){
            throw new BaseException(BaseResponseStatus.DIARY_NOT_FOUND);
        }
        return diaryList;
    }
}
