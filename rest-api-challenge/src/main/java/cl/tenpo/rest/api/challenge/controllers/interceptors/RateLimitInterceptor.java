package cl.tenpo.rest.api.challenge.controllers.interceptors;

import cl.tenpo.rest.api.challenge.exceptions.CacheNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    Bucket tokenBucket = Bucket.builder()
            .addLimit(Bandwidth.classic(3, Refill.intervally(3, Duration.ofMinutes(1))))
            .build();

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
            response.getWriter().append(String.format("{\"code\": %s, \"message\": \"%s\"}", HttpStatus.TOO_MANY_REQUESTS.value(), "Cuota de consumo " +
                    "superada. Vea X-Rate-Limit-Retry-After-Seconds"));
            return false;
        }
    }
}
