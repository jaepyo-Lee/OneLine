package com.needle.oneline.src.common;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    //1000번 성공
    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    //2000번 Request오류
    //2000~2019 user관련 오류
    USER_NOT_FOUND(false, 2000, "없는 사용자입니다."),
    USER_NOT_HAVING_DIARY(false,2001,"해당유저는 일기를 가지고 있지 않습니다."),
    //2020~2039 diary관련 오류
    DIARY_NOT_FOUND(false,2002,"해당하는 다이어리가 존재하지 않습니다."),
    DIARY_FOUND_IN_DATE(false,2003,"해당 날짜에는 일기가 존재합니다."),
    //2900~2999 common 오류
    NOT_EQUAL_DATE(false,2900,"날짜가 일치하지 않습니다."),
    REQUEST_NOT_FULFILL(false,2901,"request data가 올바르지 않습니다."),
    DIARY_LENGTH_ERROR(false,2902,"다이어리 길이가 올바르지 않습니다."),
    DIARY_REQUEST_ERROR(false,2903,"다이어리 길이 request값이 잘못되었습니다."),
    SOCIAL_TOKEN_INVALID(false,2903,"유효하지 않은 social access token입니다.");
    //3000번 Response오류
    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
