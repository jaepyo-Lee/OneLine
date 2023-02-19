package com.needle.oneline.src.diary;

import com.needle.oneline.src.common.BaseException;
import com.needle.oneline.src.common.BaseResponseStatus;
import com.needle.oneline.src.diary.dto.request.FindContentsRequestDto;
import com.needle.oneline.src.diary.dto.response.DiaryContentsResponseDto;
import com.needle.oneline.src.diary.repo.CustomDiaryRepositoryImpl;
import com.needle.oneline.src.diary.repo.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.needle.oneline.src.common.BaseResponseStatus.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class DiaryService {
    private final CustomDiaryRepositoryImpl customDiaryRepository;

    @Transactional
    public DiaryContentsResponseDto findContents(Long userId,FindContentsRequestDto requestDto) throws Exception{
        List<String> contents = customDiaryRepository.contentByUserIdAndDate(requestDto.getLocalDate(), userId);
        if(contents.isEmpty()){
            throw new BaseException(BaseResponseStatus.NOT_EQUAL_DATE);
        }
        return new DiaryContentsResponseDto(contents);

    }
}
