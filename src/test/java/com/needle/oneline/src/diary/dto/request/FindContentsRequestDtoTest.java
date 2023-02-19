package com.needle.oneline.src.diary.dto.request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.time.LocalDate.now;

class FindContentsRequestDtoTest {

    @Test
    void LocalDate_반환_테스트() {
        //given
        LocalDate localDate = now();
        //when
        FindContentsRequestDto dto = new FindContentsRequestDto(localDate);
        //then
        Assertions.assertThat(dto.getLocalDate()).isEqualTo(localDate);
    }
}