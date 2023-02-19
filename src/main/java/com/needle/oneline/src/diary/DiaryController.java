package com.needle.oneline.src.diary;

import com.needle.oneline.src.common.BaseException;
import com.needle.oneline.src.common.BaseResponse;
import com.needle.oneline.src.diary.dto.request.FindContentsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class DiaryController {
    private final DiaryService diaryService;

    @GetMapping("/users/{userId}/diaries")
    public BaseResponse findContents(@PathVariable Long userId, @RequestBody FindContentsRequestDto requestDto) throws Exception {
        try{
            return new BaseResponse(diaryService.findContents(userId, requestDto));
        }catch (BaseException e){
            e.printStackTrace();
            return new BaseResponse(e.getResponseStatus());
        }
    }
}
