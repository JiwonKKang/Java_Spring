package tobyspring.hellospring.exrate;

import java.math.BigDecimal;

public interface ExRateProvider {

    BigDecimal getExRate(String currency);
}
