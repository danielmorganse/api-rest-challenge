package cl.tenpo.rest.api.challenge;

import cl.tenpo.rest.api.challenge.utils.converters.LocalDateConverter;
import cl.tenpo.rest.api.challenge.utils.converters.LocalDateTimeConverter;
import com.fasterxml.jackson.databind.Module;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = {
        "cl.tenpo.rest.api.challenge",
        "io.swagger.configuration"
})
@EnableFeignClients
public class RestApiChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiChallengeApplication.class, args);
    }

    @Bean
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

    @Configuration
    static class CustomDateConfig implements WebMvcConfigurer {
        @Override
        public void addFormatters(FormatterRegistry registry) {
            registry.addConverter(new LocalDateConverter("yyyy-MM-dd"));
            registry.addConverter(new LocalDateTimeConverter("yyyy-MM-dd'T'HH:mm:ss.SSS"));
        }
    }
}
