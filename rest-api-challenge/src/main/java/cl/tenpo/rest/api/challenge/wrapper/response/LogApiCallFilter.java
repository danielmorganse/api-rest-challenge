package cl.tenpo.rest.api.challenge.wrapper.response;

import cl.tenpo.rest.api.challenge.config.AppConfigs;
import cl.tenpo.rest.api.challenge.utils.MapUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

@Component
public class LogApiCallFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(LogApiCallFilter.class);

    @Autowired
    private AppConfigs appConfigs;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {

        ContentCachingResponseWrapper
                responseCacheWrapperObject = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);
        filterChain.doFilter(servletRequest, responseCacheWrapperObject);

        if (!shouldNotFilter((HttpServletRequest) servletRequest)) {
            byte[] responseBodyBytes = responseCacheWrapperObject.getContentAsByteArray();
            extracted((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, responseBodyBytes);
        }
        responseCacheWrapperObject.copyBodyToResponse();
    }

    private static void extracted(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
                                  byte[] responseBodyBytes) {
        try {
            HttpServletRequest request = servletRequest;
            HttpServletResponse response = servletResponse;

            String params = MapUtils.convertWithIteration(request.getParameterMap());
            String requestBody = null;
            String endpoint = request.getRequestURI();
            String method = request.getMethod();
            String status = String.valueOf(response.getStatus());
            String responseBody = null;

            try {
                requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                responseBody = new String(responseBodyBytes, StandardCharsets.UTF_8);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            //logService.logApiCall(method, endpoint, params, responseBody, status);
            log.info("method={}; endpoint={}; params={}; requestBody={}; status={}; responseBody={}",
                    method, endpoint, params, requestBody, status, responseBody);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean shouldNotFilter(HttpServletRequest request)
            throws ServletException {
        String path = request.getRequestURI();
        return !path.startsWith(this.appConfigs.getApiBasePath());
    }
}