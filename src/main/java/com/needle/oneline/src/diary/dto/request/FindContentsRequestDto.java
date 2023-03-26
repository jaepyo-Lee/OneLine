package com.needle.oneline.src.diary.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class FindContentsRequestDto {
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate diaryDate;

    @Builder
    public FindContentsRequestDto(LocalDate diaryDate){
        this.diaryDate = diaryDate;
    }
}
