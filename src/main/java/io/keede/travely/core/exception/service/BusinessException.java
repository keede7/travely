package io.keede.travely.core.exception.service;

import io.keede.travely.core.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BusinessException extends RuntimeException {

    public BusinessException() {
        super(ErrorResponse.COMMON.getMessage());
    }

    public BusinessException(final ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
    }

    public BusinessException(
            final ErrorResponse errorResponse,
            final String logging
    ) {
        super(errorResponse.getMessage());
        printErrorMessage(logging);
    }

    protected void printErrorMessage(String logging) {
        log.error(logging);
    }

}
