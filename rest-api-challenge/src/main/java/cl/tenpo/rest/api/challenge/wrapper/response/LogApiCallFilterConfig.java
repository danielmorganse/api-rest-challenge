package cl.tenpo.rest.api.challenge.wrapper.response;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogApiCallFilterConfig {

    @Bean
    public FilterRegistrationBean<LogApiCallFilter> loggingFilter() {
        FilterRegistrationBean<LogApiCallFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogApiCallFilter());
        return registrationBean;
    }
}