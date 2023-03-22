package com.needle.oneline.src.diary.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class SaveContentsRequestDto {
    private String contents;
    private char lengthFlag;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate diaryDate;
    public SaveContentsRequestDto(String contents, LocalDate localDate){
        this.contents = contents;
        this.diaryDate = localDate;
    }
}
