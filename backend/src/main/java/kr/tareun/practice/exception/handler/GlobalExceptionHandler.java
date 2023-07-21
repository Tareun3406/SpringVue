package kr.tareun.practice.exception.handler;

import kr.tareun.practice.exception.errorcode.ErrorCode;
import kr.tareun.practice.exception.exceptions.RestApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<String> handleCustomException(RestApiException exception){
        ErrorCode errorCode = exception.getErrorCode();

        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorCode.getMessage());
    }
}
