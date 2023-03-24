package com.needle.oneline.src.diary.dto.request;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateContentsRequestDtoTest {
    @Test
    void contents_반환_테스트(){
        //given
        String contents = "컨텐츠 테스트";
        LocalDate now = LocalDate.now();
        //when
        UpdateContentsRequestDto dto = new UpdateContentsRequestDto(contents, now);
        //then
        assertThat(dto.getContents()).isEqualTo(contents);
        assertThat(dto.getDiaryDate()).isEqualTo(now);
    }

}