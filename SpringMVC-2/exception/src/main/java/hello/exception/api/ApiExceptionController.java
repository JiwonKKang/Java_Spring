package hello.exception.api;

import hello.exception.exception.BadRequestException;
import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ApiExceptionController {


    @ExceptionHandler
    public ResponseEntity<ErrorResult> ex(IllegalArgumentException e) {
        ErrorResult errorResult = new ErrorResult("Illegal", "잘못된 입력 값입니다.");
        return ResponseEntity.ok().body(errorResult);
    }

    @GetMapping("/api/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }
        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }

        if (id.equals("ex2")) {
            throw new UserException("잘못된 유저");
        }
        return new MemberDto(id, "hello " + id);
    }

    @GetMapping("/api/response-status-ex1")
    public String responseStatusEx1() {
        throw new BadRequestException();
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }
}
