package tobyspring.hellospring.exrate;

import org.springframework.stereotype.Component;
import tobyspring.hellospring.api.ApiTemplate;

import java.math.BigDecimal;

@Component("webApiExRateProvider")
public class WebApiExRateProvider implements ExRateProvider {
    private final ApiTemplate apiTemplate;

    public WebApiExRateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return apiTemplate.getExRate(url);
    }


}
