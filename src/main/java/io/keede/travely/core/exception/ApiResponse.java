package io.keede.travely.core.exception;


public class ApiResponse<T extends ErrorResponse> {

    private final int code;
    private final String message;

    public ApiResponse(
            final int code,
            final String message
    ) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse(
            T errors
    ) {
        this.code = errors.getCode();
        this.message = errors.getMessage();
    }

}
