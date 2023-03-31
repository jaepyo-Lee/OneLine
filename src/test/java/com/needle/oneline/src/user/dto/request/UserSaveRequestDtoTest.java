package com.needle.oneline.src.user.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserSaveRequestDtoTest {
    @Test
    @DisplayName("UserSaveReq0uestDto 테스트")
    void testUserSaveReqDto(){

        String email="test@naver.com";
        String name="test";
        String profile="testProfile";
        UserSaveRequestDto userSaveRequestDto = new UserSaveRequestDto(email,name,profile);
        assertAll(
                ()->assertEquals(userSaveRequestDto.getEmail(),email),
                ()->assertEquals(userSaveRequestDto.getName(),name),
                ()->assertEquals(userSaveRequestDto.getProfile(),profile)
        );
    }
}