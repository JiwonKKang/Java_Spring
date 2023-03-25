package hello.itemservice.validation;

import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

public class MessageCodesResolverTest {
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject() {
        String[] resolveMessageCodes = codesResolver.resolveMessageCodes("required", "item");
        for (String resolveMessageCode : resolveMessageCodes) {
            System.out.println("resolveMessageCode = " + resolveMessageCode);
        }
    }
}
