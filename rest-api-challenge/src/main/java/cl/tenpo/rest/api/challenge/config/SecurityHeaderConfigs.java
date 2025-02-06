package cl.tenpo.rest.api.challenge.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "security.header")
@Getter
@Setter
public class SecurityHeaderConfigs {
    private String accessControlAllowOrigin;
    private String accessControlAllowMethods;
    private String accessControlAllowHeaders;
    private String xContentTypeOptions;
}
