package cl.tenpo.rest.api.challenge.interceptors;

import cl.tenpo.rest.api.challenge.utils.MapUtils;
import cl.tenpo.rest.api.challenge.utils.ResponseWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class ApiCallInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(ApiCallInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) {
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
            //ResponseWrapper wrapperResponse = new ResponseWrapper(response);
            //responseBody = wrapperResponse.getCapturedResponse();

            ContentCachingResponseWrapper responseCacheWrapperObject = new ContentCachingResponseWrapper((HttpServletResponse) response);
            byte[] responseBody2 = responseCacheWrapperObject.getContentAsByteArray();
            responseBody = new String(responseBody2, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        /**
         if (response.getContentType() != null && response.getContentType().contains("application/json")) {
         responseBody = response.toString(); // Mejorar con un filtro si es necesario
         }
         */

        //logService.logApiCall(method, endpoint, params, responseBody, status);
        log.info("method={}; endpoint={}; params={}; responseBody={}; status={}",
                method, endpoint, params, responseBody, status);
    }
}
