package tobyspring.hellospring.payment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import tobyspring.hellospring.exrate.ExRateProvider;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

@Component
public class PaymentService {

    private final ExRateProvider exRateProvider;
    private final Clock clock;

    public PaymentService(@Qualifier("cachedExRateProvider") ExRateProvider exRateProvider, Clock clock) {
        this.exRateProvider = exRateProvider;
        this.clock = clock;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) {
        BigDecimal exRate = exRateProvider.getExRate(currency);
        return Payment.createPrepared(orderId, currency, foreignCurrencyAmount, exRate, LocalDateTime.now(clock));
    }
}
