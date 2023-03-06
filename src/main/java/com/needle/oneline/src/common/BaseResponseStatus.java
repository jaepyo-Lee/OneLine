package com.needle.oneline.src.common;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    //1000번 성공
    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    //2000번 Request오류
    //2000~2019 user관련 오류
    USER_NOT_FOUND(false, 2000, "없는 사용자입니다."),
    //2020~2039 diary관련 오류
    //2900~2999 common 오류
    NOT_EQUAL_DATE(false,2900,"날짜가 일치하지 않습니다."),
    REQUEST_NOT_FULFILL(false,2901,"request data가 올바르지 않습니다.");
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
