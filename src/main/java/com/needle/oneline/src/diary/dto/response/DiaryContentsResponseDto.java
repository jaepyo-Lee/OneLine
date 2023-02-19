package com.needle.oneline.src.diary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
public class DiaryContentsResponseDto {
    private List<String> content;

    @Builder
    public DiaryContentsResponseDto(List<String> content){
        this.content = content;
    }
}
