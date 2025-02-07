package cl.tenpo.rest.api.challenge.filters;

import cl.tenpo.rest.api.challenge.config.AppConfigs;
import cl.tenpo.rest.api.challenge.entities.ApiCallRecord;
import cl.tenpo.rest.api.challenge.services.ApiCallLogService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class LogApiCallFilterTest {
    @Mock
    private ApiCallLogService apiCallLogService;

    @Mock
    private AppConfigs appConfigs;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private LogApiCallFilter logApiCallFilter;

    @BeforeEach
    void setUp() {
        when(appConfigs.isApiCallLogsEnabled()).thenReturn(true);
        when(appConfigs.getApiCallLogsAllowedEndpoints()).thenReturn(java.util.List.of("/api"));
    }

    @Test
    void testDoFilter_WhenLoggingIsDisabled() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/api/test");
        logApiCallFilter.doFilter(request, response, filterChain);
        verify(apiCallLogService, never()).save(any(ApiCallRecord.class));
    }

    @Test
    void testDoFilter_WhenLoggingIsEnabled() throws ServletException, IOException {
        when(response.getStatus()).thenReturn(200);
        when(request.getRequestURI()).thenReturn("/api/test");
        when(request.getMethod()).thenReturn("GET");

        logApiCallFilter.doFilter(request, response, filterChain);

        verify(apiCallLogService, atMostOnce()).save(any(ApiCallRecord.class));
    }
}