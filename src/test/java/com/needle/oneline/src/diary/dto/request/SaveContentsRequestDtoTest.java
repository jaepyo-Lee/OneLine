package com.needle.oneline.src.diary.dto.request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class SaveContentsRequestDtoTest {
    @Test
    void contents_반환_테스트(){
        //given
        String contents = "테스트";
        LocalDate localDate = LocalDate.now();
        String lengthFlag = "S";
        //when
        SaveContentsRequestDto dto = new SaveContentsRequestDto(contents,localDate,lengthFlag);
        //then
        Assertions.assertThat(dto.getContents()).isEqualTo(contents);
        Assertions.assertThat(dto.getDiaryDate()).isEqualTo(localDate);
        Assertions.assertThat(dto.getLengthFlag()).isEqualTo(lengthFlag);
    }
}