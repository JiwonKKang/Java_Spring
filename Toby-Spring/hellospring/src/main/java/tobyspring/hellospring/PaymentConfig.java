package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
@ComponentScan
public class PaymentConfig {

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

//    @Bean
//    public PaymentService paymentService() {
//        return new PaymentService(cachedExRateProvider());
//    }
//
//    @Bean
//    public CachedExRateProvider cachedExRateProvider() {
//        return new CachedExRateProvider(exRateProvider());
//    }
//
//    @Bean
//    public ExRateProvider exRateProvider() {
//        return new WebApiExRateProvider();
//    }
}
