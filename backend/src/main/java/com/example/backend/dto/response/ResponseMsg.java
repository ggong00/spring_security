package com.example.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public enum ResponseMsg {
    FAIL("-99", "응답에 문제가 생겼습니다."),
    SUCCESS("00","응답이 정상적으로 이루어졌습니다."),
    UN_AUTHORIZED("1","인증이 필요합니다."),
    ACCESS_DENIED("2","접근권한이 없습니다."),
    LOGIN_FAIL("3","로그인에 실패했습니다."),
    LOGIN_SUCCESS("4","로그인에 성공했습니다.");

    private String code;
    private String message;
}
