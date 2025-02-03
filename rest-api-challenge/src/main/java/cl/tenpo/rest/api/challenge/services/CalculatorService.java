package cl.tenpo.rest.api.challenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    @Autowired
    DynamicPercentageService dynamicPercentageService;

    public Double calculate(Double num1, Double num2) {
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
