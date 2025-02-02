package cl.tenpo.rest.api.challenge.services;

import cl.tenpo.rest.api.challenge.rest.clients.externalpercentage.ExternalPercentageClient;
import cl.tenpo.rest.api.challenge.rest.clients.externalpercentage.dtos.ResultExternalPercentage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatorService {
    @Autowired ExternalPercentageClient externalPercentageClient;

    public Double calculate(Double num1, Double num2) {
        ResultExternalPercentage resultExternalPercentage = externalPercentageClient.getPercentage();
        Double percentage = resultExternalPercentage.getPercentage();
        double result = Double.sum(num1, num2);
        result = (result * percentage) + result;
        return result;
    }
}
