package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckedTest {

    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }

    static class Service {

    }

    static class Repository {
        public void call() throws MyCheckedException { //예외라는것은 잡아서 처리하거나 던지거나 둘중하나해야해서 던질거면 선언을 해야한다.
            throw new MyCheckedException("ex");
        }
    }

}
