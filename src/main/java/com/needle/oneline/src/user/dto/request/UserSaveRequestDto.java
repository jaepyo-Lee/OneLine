package com.needle.oneline.src.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserSaveRequestDto {
    private String email;
    private String name;
    private String profile;
}
