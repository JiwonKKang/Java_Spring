package hello.typeconverter.fommater;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Locale;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MyNumberFormatterTest {

    MyNumberFormatter myNumberFormatter = new MyNumberFormatter();

    @Test
    public void parse() throws ParseException {
        Number parse = myNumberFormatter.parse("1,000", Locale.KOREA);
        assertThat(parse).isEqualTo(1000L);
    }

    @Test
    public void print() {
        String print = myNumberFormatter.print(1000, Locale.KOREA);
        assertThat(print).isEqualTo("1,000");
    }
}