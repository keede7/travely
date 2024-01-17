package io.keede.travely.core.exception.service;

import io.keede.travely.core.exception.ApiResponse;
import io.keede.travely.core.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BusinessException extends RuntimeException {

    private final ErrorResponse errorResponse;

    public BusinessException() {
        super(ErrorResponse.COMMON.getMessage());
        this.errorResponse = ErrorResponse.COMMON;
    }

    public BusinessException(final ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }

    public BusinessException(
            final ErrorResponse errorResponse,
            final String logging
    ) {
        super(errorResponse.getMessage());
        printErrorMessage(logging);
        this.errorResponse = errorResponse;
    }

    protected void printErrorMessage(String logging) {
        log.error(logging);
    }

    public ApiResponse<ErrorResponse> toApiResponse() {
        return new ApiResponse<>(
                this.errorResponse
        );
    }

}