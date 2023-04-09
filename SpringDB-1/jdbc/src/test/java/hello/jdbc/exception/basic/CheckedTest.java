package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class CheckedTest {

    @Test
    void checked_catch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void checked_throw() {
        Service service = new Service();
        Assertions.assertThatThrownBy(() -> service.callThrow()).isInstanceOf(MyCheckedException.class);
    }


    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }

    /**
     * Checked 예외는
     * 예외를 잡거나 던지거나 둘중 하나를 무조건 해야한다.
     */
    static class Service {
        Repository repository = new Repository();

        public void callCatch() {
            try {
                repository.call();
            } catch (MyCheckedException e) {
                log.info("예외 처리, message={}", e);
            }
        }

        public void callThrow() throws MyCheckedException {
            throw new MyCheckedException("ex");
        }
    }
  
    static class Repository {
        public void call() throws MyCheckedException { //예외라는것은 잡아서 처리하거나 던지거나 둘중하나해야해서 던질거면 선언을 해야한다.
            throw new MyCheckedException("예외가 발생하였습니다.");
        }
    }

}
