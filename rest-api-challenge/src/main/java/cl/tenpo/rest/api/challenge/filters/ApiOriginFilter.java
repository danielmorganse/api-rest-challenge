package cl.tenpo.rest.api.challenge.filters;

import cl.tenpo.rest.api.challenge.config.SecurityHeaderConfigs;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApiOriginFilter implements Filter {

    @Autowired
    private SecurityHeaderConfigs securityHeaderConfigs;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.addHeader("Access-Control-Allow-Origin", this.securityHeaderConfigs.getAccessControlAllowOrigin());
        res.addHeader("Access-Control-Allow-Methods", this.securityHeaderConfigs.getAccessControlAllowMethods());
        res.addHeader("Access-Control-Allow-Headers", this.securityHeaderConfigs.getAccessControlAllowHeaders());
        res.addHeader("X-Content-Type-Options", this.securityHeaderConfigs.getXContentTypeOptions());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
