package com.eunoia.common.response;

import com.eunoia.common.error.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApiResponse<T> {

    private final boolean success; // 성공 여부
    private final String code; // "SUCCESS", "AUTH_REQUIRED" 같은 문자열 코드
    private final String message; // 사용자/프론트에 보여줄 메시지
    private final T data; // 실제 데이터 (성공이면 값, 실패면 null)

    // ✅ 성공 응답 (데이터 포함)
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .code("SUCCESS")
                .message("요청이 성공했습니다.")
                .data(data)
                .build();
    }

    // ✅ 성공 응답 (데이터 없음 - 예: 삭제 완료)
    public static ApiResponse<Void> success() {
        return ApiResponse.<Void>builder()
                .success(true)
                .code("SUCCESS")
                .message("요청이 성공했습니다.")
                .data(null)
                .build();
    }

    // ❌ 실패 응답 (ErrorCode 기반)
    public static <T> ApiResponse<T> fail(ErrorCode errorCode) {
        return ApiResponse.<T>builder()
                .success(false)
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .data(null)
                .build();
    }

    // ❌ 실패 응답 (ErrorCode + 커스텀 메시지)
    public static <T> ApiResponse<T> fail(ErrorCode errorCode, String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .code(errorCode.getCode())
                .message(message)
                .data(null)
                .build();
    }

}
