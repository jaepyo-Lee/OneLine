package com.needle.oneline.src.diary.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class UpdateContentsRequestDto {

    @PastOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    LocalDate diaryDate;

    @NotNull
    private String contents;

    @NotBlank
    private String lengthFlag;

    public UpdateContentsRequestDto(String contents,LocalDate diaryDate){
        this.contents = contents;
        this.diaryDate = diaryDate;
    }
}
