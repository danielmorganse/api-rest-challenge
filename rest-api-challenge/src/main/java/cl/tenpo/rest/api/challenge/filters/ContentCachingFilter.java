package cl.tenpo.rest.api.challenge.filters;


import cl.tenpo.rest.api.challenge.config.AppConfigs;
import cl.tenpo.rest.api.challenge.wrapper.request.CachedBodyHttpServletRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
@WebFilter(filterName = "ContentCachingFilter")
public class ContentCachingFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(ContentCachingFilter.class);

    private AppConfigs appConfigs;

    @Autowired
    public ContentCachingFilter(AppConfigs appConfigs) {
        this.appConfigs = appConfigs;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws
            ServletException, IOException {
        log.debug("IN  ContentCachingFilter ");
        CachedBodyHttpServletRequest cachedBodyHttpServletRequest =
                new CachedBodyHttpServletRequest(httpServletRequest);
        filterChain.doFilter(cachedBodyHttpServletRequest, httpServletResponse);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)
            throws ServletException {
        String path = request.getRequestURI();
        return !path.startsWith(this.appConfigs.getApiBasePath());
    }
}