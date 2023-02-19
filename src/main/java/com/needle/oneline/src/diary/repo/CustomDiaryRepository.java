package com.needle.oneline.src.diary.repo;

import com.needle.oneline.src.common.BaseException;

import java.time.LocalDate;
import java.util.List;

public interface CustomDiaryRepository {

    List<String> contentByUserIdAndDate(LocalDate localDate, Long userId) throws BaseException;
}
