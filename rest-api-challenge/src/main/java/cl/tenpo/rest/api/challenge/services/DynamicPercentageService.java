package cl.tenpo.rest.api.challenge.services;

import cl.tenpo.rest.api.challenge.exceptions.CacheNotFoundException;
import cl.tenpo.rest.api.challenge.rest.clients.externalpercentage.ExternalPercentageClient;
import cl.tenpo.rest.api.challenge.rest.clients.externalpercentage.dtos.ResultExternalPercentage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DynamicPercentageService {
    @Autowired ExternalPercentageClient externalPercentageClient;

    @CachePut(value = "cache1")
    public Double getPercentage() {
        ResultExternalPercentage resultExternalPercentage = externalPercentageClient.getPercentage();
        Double percentage = resultExternalPercentage.getPercentage();
        return percentage;
    }

    @Cacheable(value = "cache1")
    public Double getCachePercentage() throws CacheNotFoundException {
        throw new CacheNotFoundException("Porcentaje dinámico no disponible en caché");
    }
}
