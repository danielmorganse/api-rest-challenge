package cl.tenpo.rest.api.challenge.config;

import feign.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalPercentageFeignClientConfiguration {
    @Value("${feign.client.config.externalPercentageClient.connectTimeout}")
    private int connectTimeout = 2000;
    @Value("${feign.client.config.externalPercentageClient.readTimeout}")
    private int readTimeout = 2000;

    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeout, readTimeout); // 5 seconds connect, 10 seconds read
    }
}
