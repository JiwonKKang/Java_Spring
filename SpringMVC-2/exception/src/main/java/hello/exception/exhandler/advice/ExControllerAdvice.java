package hello.exception.exhandler.advice;

import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<ErrorResult> ex(IllegalArgumentException e) {
        ErrorResult errorResult = new ErrorResult("Illegal", "잘못된 입력 값입니다.");
        return ResponseEntity.ok().body(errorResult);
    }

    @ExceptionHandler
    public ErrorResult ex2(RuntimeException e) {
        ErrorResult errorResult = new ErrorResult("Run", "RunTimeException");
        return errorResult;
    }
}
