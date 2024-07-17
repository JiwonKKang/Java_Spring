package tobyspring.hellospring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class ApiTemplate {

    private final ApiExecutor defaultApiExecutor;
    private final ExRateExtractor defaultExRateExtractor;

    public ApiTemplate(ApiExecutor defaultApiExecutor, ExRateExtractor defaultExRateExtractor) {
        this.defaultApiExecutor = defaultApiExecutor;
        this.defaultExRateExtractor = defaultExRateExtractor;
    }

    public BigDecimal getExRate(String url) {
        return getExRate(url, defaultApiExecutor, defaultExRateExtractor);
    }

    public BigDecimal getExRate(String url, ApiExecutor apiExecutor) {
        return getExRate(url, apiExecutor, defaultExRateExtractor);
    }

    public BigDecimal getExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;

        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return exRateExtractor.extract(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
