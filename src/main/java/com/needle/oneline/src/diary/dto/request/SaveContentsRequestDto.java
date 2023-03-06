package com.needle.oneline.src.diary.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SaveContentsRequestDto {
    private String contents;
    private char lengthFlag;
    public SaveContentsRequestDto(String contents){
        this.contents = contents;
    }
}
