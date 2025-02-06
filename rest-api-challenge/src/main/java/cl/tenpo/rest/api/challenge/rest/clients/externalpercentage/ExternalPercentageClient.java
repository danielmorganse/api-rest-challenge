package cl.tenpo.rest.api.challenge.rest.clients.externalpercentage;

import cl.tenpo.rest.api.challenge.config.ExternalPercentageFeignClientConfiguration;
import cl.tenpo.rest.api.challenge.rest.clients.externalpercentage.dtos.ResultExternalPercentage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        value = "externalPercentageClient",
        url = "${rest.clients.externalPercentageClient.url}",
        configuration = ExternalPercentageFeignClientConfiguration.class)
public interface ExternalPercentageClient {
    @GetMapping(value = "/external-percentage", produces = "application/json")
    ResultExternalPercentage getPercentage();
}
