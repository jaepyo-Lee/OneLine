package com.needle.oneline.src.diary.dto.request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaveContentsRequestDtoTest {
    @Test
    void contents_반환_테스트(){
        //given
        String contents = "테스트";
        //when
        SaveContentsRequestDto dto = new SaveContentsRequestDto(contents);
        //then
        Assertions.assertThat(dto.getContents()).isEqualTo(contents);
    }
}