package cl.tenpo.rest.api.challenge.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class AppConfigs {

    @Value("${api.base-path}")
    private String apiBasePath;

    @Value("${apicalls.log.enabled}")
    private boolean apiCallLogsEnabled = false;

    @Value("${apicalls.log.allowed-endpoints}")
    private List<String> apiCallLogsAllowedEndpoints = new ArrayList<>();

    @Value("${ratelimit.enabled}")
    private boolean rateLimitEnabled = false;

    @Value("${ratelimit.allowed-patterns}")
    private List<String> rateLimitAllowedPatterns = new ArrayList<>();

}
