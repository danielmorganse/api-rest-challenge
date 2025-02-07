package cl.tenpo.rest.api.challenge.services;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CalculatorService {

    DynamicPercentageService dynamicPercentageService;

    @Autowired
    public CalculatorService(DynamicPercentageService dynamicPercentageService) {
        this.dynamicPercentageService = dynamicPercentageService;
    }

    public Double calculate(@NonNull Double num1, @NonNull Double num2) {
        Double percentage;
        try {
            percentage = this.dynamicPercentageService.getPercentage();
        } catch (Exception ex) { //Reemplazar por excepción de reintentos fallidos
            percentage = this.dynamicPercentageService.getCachePercentage();
        }
        double result = Double.sum(num1, num2);
        result = (result * percentage) + result;
        return result;
    }
}
