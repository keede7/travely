package io.keede.travely.core.exception;


public enum ErrorResponse {
    COMMON(1000, "요청에 실패했습니다."),

    EXCEED_RESERVATION_USER_COUNT(2000, "최대 예약 인원 수를 초과했습니다."),

    ;
    private final int code;

    private final String message;

    ErrorResponse(
            final int code,
            final String message
    ) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
