package com.starbus.starbus.Protocol;

public enum ResponseType {
    FAIL (0, "명령을 실행하지 못했습니다"),
    VALIDATE_ERROR (1, "검증 오류"),

    USER_REGISTER_SUCCESS (101, "회원가입에 성공하였습니다"),
    USER_LOGIN_SUCCESS (102, "로그인에 성공하였습니다"),

    GET_ROUTES_SUCCESS (201, "노선 조회에 성공하였습니다"),
    APPLY_ROUTE_SUCCESS (202, "노선 승차 신청에 성공하였습니다"),

    GET_CITIES_SUCCESS (301, "도시 코드 조회에 성공하였습니다");

    final private int code;
    final private String desc;
    ResponseType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int code() {
        return this.code;
    }

    public String desc() {
        return this.desc;
    }
}
