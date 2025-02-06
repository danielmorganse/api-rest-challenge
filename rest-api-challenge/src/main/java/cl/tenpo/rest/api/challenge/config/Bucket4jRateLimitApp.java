package cl.tenpo.rest.api.challenge.config;

import cl.tenpo.rest.api.challenge.interceptors.RateLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class Bucket4jRateLimitApp implements WebMvcConfigurer {

    private AppConfigs appConfigs;

    private RateLimitInterceptor interceptor;

    @Autowired
    public Bucket4jRateLimitApp(AppConfigs appConfigs, RateLimitInterceptor interceptor) {
        this.appConfigs = appConfigs;
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (this.appConfigs.isRateLimitEnabled()) {
            registry.addInterceptor(interceptor)
                    .addPathPatterns(this.appConfigs.getRateLimitAllowedPatterns());
        }
    }
}
