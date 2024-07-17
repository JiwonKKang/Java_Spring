package tobyspring.hellospring.exrate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component("cachedExRateProvider")
public class CachedExRateProvider implements ExRateProvider {

    private final ExRateProvider target;

    private BigDecimal cachedExRate;
    private LocalDateTime cacheExpiryTime;

    public CachedExRateProvider(@Qualifier("webApiExRateProvider") ExRateProvider exRateProvider) {
        this.target = exRateProvider;
    }

    @Override
    public BigDecimal getExRate(String currency) {

        if (cachedExRate == null || cacheExpiryTime.isBefore(LocalDateTime.now())) {
            cachedExRate = target.getExRate(currency);
            cacheExpiryTime = LocalDateTime.now().plusSeconds(3);
            System.out.println("exRate is cached");
        }

        return cachedExRate;
    }
}
