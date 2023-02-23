package com.needle.oneline.src.diary.repo;

import com.needle.oneline.src.common.BaseException;
import com.needle.oneline.src.diary.Diary;

import java.time.LocalDate;
import java.util.List;

public interface CustomDiaryRepository {
    List<Diary> diaryListFindByUserId(Long userId) throws BaseException;

    Diary diaryFindByUserIdAndDate(Long userId, LocalDate localDate)throws BaseException;
    List<String> diaryContentByUserIdAndDate(LocalDate localDate, Long userId) throws BaseException;
}
