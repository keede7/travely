package io.keede.travely.core.exception;


import io.keede.travely.core.exception.service.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author kyh
 * Created on 2024/01/16
 */
@Slf4j
@RestControllerAdvice
public final class ApiErrorAdvice {

    @ExceptionHandler({ BusinessException.class })
    public ApiResponse<ErrorResponse> handleBusinessException(BusinessException e) {
        return e.toApiResponse();
    }

}
