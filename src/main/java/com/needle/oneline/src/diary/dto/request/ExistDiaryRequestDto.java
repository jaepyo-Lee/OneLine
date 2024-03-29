package com.needle.oneline.src.diary.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ExistDiaryRequestDto {

    @PastOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate diaryDate;

    public ExistDiaryRequestDto(LocalDate diaryDate){
        this.diaryDate = diaryDate;
    }
}
