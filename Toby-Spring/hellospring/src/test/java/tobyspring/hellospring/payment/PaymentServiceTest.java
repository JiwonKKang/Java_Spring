package tobyspring.hellospring.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.*;


class PaymentServiceTest {

    Clock clock;

    @BeforeEach
    public void init() {
        clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 만족하는지 검증")
    void prepare() throws IOException {

        testAmount(BigDecimal.valueOf(1200), BigDecimal.valueOf(12000));
        testAmount(BigDecimal.valueOf(1300), BigDecimal.valueOf(13000));
    }

    private void testAmount(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        //환율정보 가져온다
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        //원화환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualTo(convertedAmount);
        //시간 검증
        assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
    }


}