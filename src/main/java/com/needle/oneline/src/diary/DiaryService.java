package com.needle.oneline.src.diary;

import com.needle.oneline.src.common.BaseException;
import com.needle.oneline.src.common.BaseResponseStatus;
import com.needle.oneline.src.diary.dto.request.ExistDiaryRequestDto;
import com.needle.oneline.src.diary.dto.request.FindContentsRequestDto;
import com.needle.oneline.src.diary.dto.request.SaveContentsRequestDto;
import com.needle.oneline.src.diary.dto.request.UpdateContentsRequestDto;
import com.needle.oneline.src.diary.dto.response.DiaryContentsResponseDto;
import com.needle.oneline.src.diary.repo.CustomDiaryRepositoryImpl;
import com.needle.oneline.src.diary.repo.DiaryRepository;
import com.needle.oneline.src.user.User;
import com.needle.oneline.src.user.repo.UserRepository;
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
    private final UserRepository userRepository;
    private final DiaryRepository diaryRepository;

    @Transactional
    public DiaryContentsResponseDto findContents(Long userId,FindContentsRequestDto requestDto) throws Exception{
        List<String> contents = customDiaryRepository.diaryContentByUserIdAndDate(requestDto.getLocalDate(), userId);
        if(contents.isEmpty()){
            throw new BaseException(BaseResponseStatus.NOT_EQUAL_DATE);
        }
        return new DiaryContentsResponseDto(contents);

    }

    @Transactional
    public boolean existDiary(Long userId, ExistDiaryRequestDto requestDto) throws Exception{
        return customDiaryRepository.existDiaryByUserIdAndDate(userId, requestDto.getLocalDate());
    }

    @Transactional
    public String saveContents(Long userId, SaveContentsRequestDto requestDto) throws Exception {
        User userById = userRepository.findById(userId)
                .orElseThrow(()->new BaseException(USER_NOT_FOUND));
        Diary saveDiary = diaryRepository.save(new Diary(userById, requestDto.getContents()));
        return saveDiary.getContents();
    }

    @Transactional
    public String modifyContents(Long userId, UpdateContentsRequestDto requestDto) throws Exception {
        Diary diary = customDiaryRepository.diaryFindByUserIdAndDate(userId, requestDto.getLocalDate());
        diary.update(requestDto.getContents());
        return requestDto.getContents();
    }
}
