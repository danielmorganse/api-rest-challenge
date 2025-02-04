package cl.tenpo.rest.api.challenge.config;

import cl.tenpo.rest.api.challenge.interceptors.ApiCallInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApiCallLogConfig implements WebMvcConfigurer {

    @Autowired
    private ApiCallInterceptor apiCallInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiCallInterceptor);
    }
}
