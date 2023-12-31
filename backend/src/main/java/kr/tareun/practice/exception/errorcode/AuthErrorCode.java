package kr.tareun.practice.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements ErrorCode{

    NOT_LOGGED_IN(HttpStatus.UNAUTHORIZED, "로그인이 필요한 요청입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
