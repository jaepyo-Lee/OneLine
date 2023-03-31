package com.needle.oneline.src.user;

import com.needle.oneline.src.common.enumerate.RoleType;
import com.needle.oneline.src.common.enumerate.SnsType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.needle.oneline.src.common.enumerate.SnsType.KAKAO;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void user_생성테스트(){

        User user = userBuilder();
        assertAll(
                () -> assertEquals(1L, user.getId()),
                () -> assertEquals("test", user.getPicture()),
                () -> assertEquals("test", user.getEmail()),
                () -> assertEquals("test", user.getName()),
                () -> assertEquals(KAKAO, user.getSnsType()),
                () -> assertEquals(null, user.getDiaries()),
                () -> assertEquals("test", user.getSocialId()),
                () -> assertEquals(RoleType.USER, user.getRoleType())
        );
        }

    private static User userBuilder() {
        User user = User.builder()
                .id(1L)
                .email("test")
                .snsType(KAKAO)
                .diaries(null)
                .picture("test")
                .roleType(RoleType.USER)
                .name("test")
                .socialId("test")
                .build();
        return user;
    }
}