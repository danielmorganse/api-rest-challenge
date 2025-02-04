package cl.tenpo.rest.api.challenge.wrapper.response;

import cl.tenpo.rest.api.challenge.utils.MapUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {
        ContentCachingResponseWrapper
                responseCacheWrapperObject = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);
        filterChain.doFilter(servletRequest, responseCacheWrapperObject);

        byte[] responseBodyBytes = responseCacheWrapperObject.getContentAsByteArray();

        try {
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            byte[] md5Hash = md5Digest.digest(responseBodyBytes);
            String md5HashString = DatatypeConverter.printHexBinary(md5Hash);
            responseCacheWrapperObject.setHeader("Response-Body-MD5", md5HashString);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        responseCacheWrapperObject.copyBodyToResponse();

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

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
        log.info("method={}; endpoint={}; params={}; requestBody={}; responseBody={}; status={}",
                method, endpoint, params, requestBody, responseBody, status);
    }
}