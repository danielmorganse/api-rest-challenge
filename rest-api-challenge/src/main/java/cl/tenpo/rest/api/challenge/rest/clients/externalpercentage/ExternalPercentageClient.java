package cl.tenpo.rest.api.challenge.rest.clients.externalpercentage;

import cl.tenpo.rest.api.challenge.rest.clients.externalpercentage.dtos.ResultExternalPercentage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "externalPercentageClient", url = "${rest.clients.externalPercentageClient.url}")
public interface ExternalPercentageClient {
    @RequestMapping(method = RequestMethod.GET, value = "/external-percentage", produces = "application/json")
    ResultExternalPercentage getPercentage();
}
