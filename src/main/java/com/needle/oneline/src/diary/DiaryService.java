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
import java.util.List;

import static com.needle.oneline.src.common.BaseResponseStatus.DIARY_NOT_FOUND;
import static com.needle.oneline.src.common.BaseResponseStatus.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class DiaryService {
    private final CustomDiaryRepositoryImpl customDiaryRepository;
    private final UserRepository userRepository;
    private final DiaryRepository diaryRepository;

    public DiaryContentsResponseDto findContents(Long userId,FindContentsRequestDto requestDto) throws Exception{
        List<String> contents = customDiaryRepository.diaryContentByUserIdAndDate(requestDto.getDiaryDate(), userId);
        if(contents.isEmpty()){
            throw new BaseException(BaseResponseStatus.NOT_EQUAL_DATE);
        }
        return new DiaryContentsResponseDto(contents);

    }

    public boolean existDiary(Long userId, ExistDiaryRequestDto requestDto) throws Exception{
        return customDiaryRepository.existDiaryByUserIdAndDate(userId, requestDto.getDiaryDate());
    }

    @Transactional
    public String saveContents(Long userId, SaveContentsRequestDto requestDto) throws Exception {
        User userById = userRepository.findById(userId)
                .orElseThrow(()->new BaseException(USER_NOT_FOUND));
        if(verifyContentsLength(requestDto.getContents(), requestDto.getLengthFlag())){
            if(existDiary(userId, new ExistDiaryRequestDto(requestDto.getDiaryDate()))){//같은 날짜에 두개의 게시물이 있으면 안됨으로 처리
                throw new BaseException(BaseResponseStatus.DIARY_FOUND_IN_DATE);
            }
            Diary saveDiary = diaryRepository.save(new Diary(userById, requestDto.getContents(),
                    requestDto.getLengthFlag(), requestDto.getDiaryDate()));
            return saveDiary.getContents();

        }
        throw new BaseException(BaseResponseStatus.DIARY_LENGTH_ERROR);
    }

    @Transactional
    public String modifyContents(Long userId, UpdateContentsRequestDto requestDto) throws Exception {
        List<Diary> diary = customDiaryRepository.diaryFindByUserIdAndDate(userId, requestDto.getDiaryDate());
        verifyContentsLength(requestDto.getContents(), requestDto.getLengthFlag());
        if(diary.isEmpty()){
            throw new BaseException(DIARY_NOT_FOUND);
        }
        diary.get(0).update(requestDto.getContents(), requestDto.getLengthFlag());
        return requestDto.getContents();
    }
    /*
    * 다이어리 길이 확인 메서드
    * */
    public boolean verifyContentsLength(String contents,String lengthFlag) throws BaseException {
        if(lengthFlag.equals("L")){
            if(contents.length()<=100){
                return false;
            }else{
                return true;
            }
        }else if(lengthFlag.equals("S")){
            if(contents.length()<=100){
                return true;
            }else{
                return false;
            }
        }
        throw new BaseException(BaseResponseStatus.DIARY_REQUEST_ERROR);
    }
}
