package com.needle.oneline.src.diary;

import com.needle.oneline.src.common.BaseException;
import com.needle.oneline.src.common.BaseResponse;
import com.needle.oneline.src.diary.dto.request.ExistDiaryRequestDto;
import com.needle.oneline.src.diary.dto.request.FindContentsRequestDto;
import com.needle.oneline.src.diary.dto.request.SaveContentsRequestDto;
import com.needle.oneline.src.diary.dto.request.UpdateContentsRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Diary",description = "일기관련api")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class DiaryController {
    private final DiaryService diaryService;

    @GetMapping("/{userId}/diary/content")
    @Operation(summary = "일기내용조회api",description = "유저id와, 해당하는 날짜를 yyyy-mm-dd 형식으로 보내주면 해당 날짜의 일기가 반환됨")
    public BaseResponse findDiary(@PathVariable Long userId, @RequestBody FindContentsRequestDto requestDto) throws Exception {
        try{
            return new BaseResponse(diaryService.findContents(userId, requestDto));
        }catch (BaseException e){
            e.printStackTrace();
            return new BaseResponse(e.getResponseStatus());
        }
    }

    @GetMapping("/{userId}/diary/exist")
    @Operation(summary = "일기존재확인",description = "유저id와, 해당하는 날짜를 yyyy-mm-dd 형식으로 보내주면 해당 날짜에 일기를 썻는지 확인됨" +
            "result에 false면 존재안함, true이면 존재")
    public BaseResponse existDiary(@PathVariable Long userId,@RequestBody ExistDiaryRequestDto requestDto){
        try{
            return new BaseResponse(diaryService.existDiary(userId, requestDto));
        }catch (BaseException e){
            return new BaseResponse(e.getResponseStatus());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{userId}/diary/content")
    @Operation(summary = "일기저장api",description = "작성한 일기내용과 긴 일기라면 L, 짧으면 S 보내주면 됨!" )
    public BaseResponse saveDiary(@PathVariable Long userId, @RequestBody @Valid SaveContentsRequestDto requestDto) throws Exception{
        try{
            return new BaseResponse(diaryService.saveContents(userId, requestDto));
        }catch (BaseException e){
            return new BaseResponse(e.getResponseStatus());
        }
    }

    @PatchMapping("/{userId}/diary/content")
    @Operation(summary = "일기수정api",description = "수정할 일기내용 + 수정할 일기의 날짜를 yyyy-mm-dd 형태로 보내주면 됨!")
    public BaseResponse modifiedDiary(@PathVariable Long userId, @RequestBody UpdateContentsRequestDto requestDto) throws Exception{
        try{
            return new BaseResponse(diaryService.modifyContents(userId, requestDto));
        }catch (BaseException e){
            return new BaseResponse(e.getResponseStatus());
        }
    }

}
