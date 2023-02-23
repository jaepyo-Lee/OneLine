package com.needle.oneline.src.diary.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SaveContentsRequestDto {
    private String contents;
    public SaveContentsRequestDto(String contents){
        this.contents = contents;
    }
}
