package com.needle.oneline.src.diary;

import com.needle.oneline.src.common.BaseException;
import com.needle.oneline.src.common.BaseResponse;
import com.needle.oneline.src.diary.dto.request.FindContentsRequestDto;
import com.needle.oneline.src.diary.dto.request.SaveContentsRequestDto;
import com.needle.oneline.src.diary.dto.request.UpdateContentsRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Diary",description = "일기관련api")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class DiaryController {
    private final DiaryService diaryService;

    @GetMapping("/{userId}/diaries")
    @Operation(summary = "일기조회api")
    public BaseResponse findDiary(@PathVariable Long userId, @RequestBody FindContentsRequestDto requestDto) throws Exception {
        try{
            return new BaseResponse(diaryService.findContents(userId, requestDto));
        }catch (BaseException e){
            e.printStackTrace();
            return new BaseResponse(e.getResponseStatus());
        }
    }

    @PostMapping("/{userId}/diaries")
    @Operation(summary = "일기저장api")
    public BaseResponse saveDiary(@PathVariable Long userId, @RequestBody SaveContentsRequestDto requestDto) throws Exception{
        try{
            return new BaseResponse(diaryService.saveContents(userId, requestDto));
        }catch (BaseException e){
            return new BaseResponse(e.getResponseStatus());
        }
    }

    @PatchMapping("/{userId}/diaries")
    @Operation(summary = "일기수정api")
    public BaseResponse modifiedDiary(@PathVariable Long userId, @RequestBody UpdateContentsRequestDto requestDto) throws Exception{
        try{
            return new BaseResponse(diaryService.modifyContents(userId, requestDto));
        }catch (BaseException e){
            return new BaseResponse(e.getResponseStatus());
        }
    }
}
