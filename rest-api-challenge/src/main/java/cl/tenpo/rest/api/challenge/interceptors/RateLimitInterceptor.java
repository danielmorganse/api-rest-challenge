package cl.tenpo.rest.api.challenge.interceptors;

import cl.tenpo.rest.api.challenge.dtos.Error;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.Refill;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.math.BigDecimal;
import java.time.Duration;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    @Value("${ratelimit.capacity}")
    private int capacity = 6;
    @Value("${ratelimit.minutes}")
    private int minutes = 2;

    private Bucket tokenBucket;

    @PostConstruct
    private void postConstruct() {
        tokenBucket = Bucket.builder()
                .addLimit(Bandwidth.classic(capacity, Refill.intervally(capacity, Duration.ofMinutes(minutes))))
                .build();
    }

    private final ObjectMapper objectMapper = new ObjectMapper(); // Para convertir objetos a JSON

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        ConsumptionProbe probe = tokenBucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            response.addHeader("X-Rate-Limit-Remaining", String.valueOf(probe.getRemainingTokens()));
            return true;
        } else {
            long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;
            response.addHeader("X-Rate-Limit-Retry-After-Seconds", String.valueOf(waitForRefill));
            response.setContentType("application/json");
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            Error error = new Error().code(BigDecimal.valueOf(HttpStatus.TOO_MANY_REQUESTS.value())).message("Cuota " +
                    "de consumo superada. Vea X-Rate-Limit-Retry-After-Seconds");
            response.getWriter().append(objectMapper.writeValueAsString(error));
            return false;
        }
    }
}
