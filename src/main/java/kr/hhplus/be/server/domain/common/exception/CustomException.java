package kr.hhplus.be.server.domain.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final int errorCode;


    public CustomException( HttpStatus httpStatus, int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public CustomException( HttpStatus httpStatus, String message) {
        super(message);
        this.errorCode = httpStatus.value();
        this.httpStatus = httpStatus;
    }

    public CustomException(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = HttpStatus.BAD_REQUEST.value();// 기본값 설정
    }

    public CustomException() {
        super("잘못된 요청입니다.");
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = HttpStatus.BAD_REQUEST.value();
    }

}
