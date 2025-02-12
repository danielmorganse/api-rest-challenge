package cl.tenpo.rest.api.challenge.filters;

import cl.tenpo.rest.api.challenge.config.AppConfigs;
import cl.tenpo.rest.api.challenge.entities.ApiCallRecord;
import cl.tenpo.rest.api.challenge.services.ApiCallLogService;
import cl.tenpo.rest.api.challenge.utils.MapUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class LogApiCallFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(LogApiCallFilter.class);

    private ApiCallLogService apiCallLogService;

    private AppConfigs appConfigs;

    @Autowired
    public LogApiCallFilter(ApiCallLogService apiCallLogService, AppConfigs appConfigs) {
        this.apiCallLogService = apiCallLogService;
        this.appConfigs = appConfigs;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {

        ContentCachingResponseWrapper
                responseCacheWrapperObject = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);
        filterChain.doFilter(servletRequest, responseCacheWrapperObject);

        if (!shouldNotFilter((HttpServletRequest) servletRequest)) {
            byte[] responseBodyBytes = responseCacheWrapperObject.getContentAsByteArray();
            tryLogApiCall((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse,
                    responseBodyBytes);
        }
        responseCacheWrapperObject.copyBodyToResponse();
    }

    private void tryLogApiCall(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
                               byte[] responseBodyBytes) {
        try {
            HttpServletRequest request = servletRequest;
            HttpServletResponse response = servletResponse;

            String params = MapUtils.convertWithIteration(request.getParameterMap());
            String endpoint = request.getRequestURI();
            String method = request.getMethod();
            int status = response.getStatus();

            String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            String responseBody = new String(responseBodyBytes, StandardCharsets.UTF_8);

            ApiCallRecord entity = ApiCallRecord.builder()
                    .date(new Date())
                    .requestMethod(method)
                    .requestEndpoint(endpoint)
                    .requestParams(params)
                    .requestBody(requestBody)
                    .responseStatus(status)
                    .responseBody(responseBody)
                    .build();
            this.apiCallLogService.save(entity);

            log.info("method={}; endpoint={}; params={}; requestBody={}; status={}; responseBody={}",
                    method, endpoint, params, requestBody, status, responseBody);
        } catch (Exception e) {
            log.error("Error al intentar grabar log de llamada API", e);
        }
    }

    private boolean shouldNotFilter(HttpServletRequest request)
            throws ServletException {
        if (!this.appConfigs.isApiCallLogsEnabled()) {
            return true;
        }
        String path = request.getRequestURI();
        return this.appConfigs.getApiCallLogsAllowedEndpoints()
                .stream().allMatch(s -> !path.startsWith(s));
    }
}