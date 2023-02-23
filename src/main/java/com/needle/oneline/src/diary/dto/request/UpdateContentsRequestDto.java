package com.needle.oneline.src.diary.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class UpdateContentsRequestDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    LocalDate localDate;
    private String contents;

    public UpdateContentsRequestDto(String contents,LocalDate localDate){
        this.contents = contents;
        this.localDate = localDate;
    }
}
