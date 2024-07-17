package tobyspring.hellospring.exrate;

import java.math.BigDecimal;

public class SimpleExRateProvider implements ExRateProvider {
    @Override
    public BigDecimal getExRate(String currency) {
        if(currency.equals("USD")) {
            return BigDecimal.valueOf(1000);
        } else {
            throw new IllegalArgumentException("Unsupported currency: " + currency);
        }
    }
}
