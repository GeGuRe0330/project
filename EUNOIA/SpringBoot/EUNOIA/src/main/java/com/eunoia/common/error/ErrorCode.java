package com.eunoia.common.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // 기본 오류
    SUCCESS(HttpStatus.OK, "SUCCESS", "요청이 성공했습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "NOT_FOUND", "요청한 데이터를 찾을 수 없습니다."),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", "서버 오류가 발생했습니다."),

    // 요청 & 중복오류
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "INVALID_REQUEST", "요청이 올바르지 않습니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "DUPLICATE_EMAIL", "이미 사용 중인 이메일입니다."),

    // 권한 & 승인 오류
    AUTH_REQUIRED(HttpStatus.UNAUTHORIZED, "AUTH_REQUIRED", "로그인이 필요합니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "ACCESS_DENIED", "접근 권한이 없습니다."),
    ACCOUNT_PENDING(HttpStatus.FORBIDDEN, "ACCOUNT_PENDING", "계정 승인이 필요합니다."),

    // 작성자 & 데이터 소유권 오류
    FORBIDDEN_RESOURCE(HttpStatus.FORBIDDEN, "FORBIDDEN_RESOURCE", "해당 리소스에 대한 권한이 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
