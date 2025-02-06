package cl.tenpo.rest.api.challenge.config;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.concurrent.TimeUnit;

@Configuration
public class ExternalPercentageFeignClientConfiguration {
    @Value("${feign.client.config.externalPercentageClient.connectTimeout}")
    private int connectTimeout = 2000;
    @Value("${feign.client.config.externalPercentageClient.readTimeout}")
    private int readTimeout = 2000;
    @Value("${feign.client.config.externalPercentageClient.retries}")
    private int retries = 5;

    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeout, readTimeout); // 5 seconds connect, 10 seconds read
    }

    @Bean
    public Retryer retryer(ApplicationContext applicationContext, Environment env) {
        return new Retryer.Default(100L, TimeUnit.SECONDS.toMillis(1L), this.retries);
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
