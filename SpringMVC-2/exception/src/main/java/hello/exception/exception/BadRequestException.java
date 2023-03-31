package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "잘못된 요청 오류")
//Exception 에 따른 response 상태코드 지정
public class BadRequestException extends RuntimeException{
}