package com.needle.oneline.src.diary.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class SaveContentsRequestDto {
    @NotNull
    private String contents;

    @NotBlank
    private String lengthFlag;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @PastOrPresent
    private LocalDate diaryDate;
    public SaveContentsRequestDto(String contents, LocalDate localDate){
        this.contents = contents;
        this.diaryDate = localDate;
    }
}
