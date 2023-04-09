package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class UncheckedTest {

    @Test
    void unchecked_catch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void unchecked_throw() {
        Service service = new Service();
        Assertions.assertThatThrownBy(service::callThrow).isInstanceOf(MyUncheckedException.class);
        //읽기 어려울수 있기 때문에 람다식을 사용해서 메서드를 호출하는대신 메서드 참조방법을쓰는게 더 읽기 편함
    }

    static class MyUncheckedException extends RuntimeException {
        public MyUncheckedException(String message) {
            super(message);
        }
    }

    static class Service {
        Repository repository = new Repository();

        public void callCatch() {
            try {
                repository.call();
            } catch (MyUncheckedException e) {
                log.info("ex={}", e.getMessage());
            }

        }

        public void callThrow() {
            repository.call();
        }
    }


    static class Repository {
        public void call() {
            throw new MyUncheckedException("언체크 예외 발생");
        }
    }
}
