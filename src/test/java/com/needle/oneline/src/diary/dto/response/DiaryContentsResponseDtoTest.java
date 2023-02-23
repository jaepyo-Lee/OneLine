package com.needle.oneline.src.diary.dto.response;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiaryContentsResponseDtoTest {
    @Test
    void contents_반환_테스트(){
        //given
        List<String> contents = new ArrayList<>();
        contents.add("테스트1");
        contents.add("테스트2");
        //when
        DiaryContentsResponseDto dto = new DiaryContentsResponseDto(contents);
        //then
        Assertions.assertThat(dto.getContent()).isEqualTo(contents);
    }
}