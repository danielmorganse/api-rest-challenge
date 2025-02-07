package cl.tenpo.rest.api.challenge.services;

import cl.tenpo.rest.api.challenge.exceptions.CacheNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class CalculatorServiceTest {

    @Mock
    DynamicPercentageService dynamicPercentageService;

    @InjectMocks
    CalculatorService calculatorService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculate_withNullsParameters() {
        assertThrows(NullPointerException.class, () -> calculatorService.calculate(null, null));

        verify(dynamicPercentageService, times(0)).getPercentage();
        verify(dynamicPercentageService, times(0)).getCachePercentage();
    }

    @Test
    void calculate_withValidParameters_withNoService_cache() {
        Double num = Double.valueOf(5.0);
        Double result = Double.valueOf(11.0);
        when(this.dynamicPercentageService.getPercentage()).thenThrow(RuntimeException.class);
        when(this.dynamicPercentageService.getCachePercentage()).thenReturn(Double.valueOf(0.1));

        assertEquals(this.calculatorService.calculate(num, num), result);

        verify(dynamicPercentageService, times(1)).getPercentage();
        verify(dynamicPercentageService, times(1)).getCachePercentage();
    }

    @Test
    void calculate_withValidParameters_withNoService_noCache() {
        Double num = Double.valueOf(5.0);
        when(this.dynamicPercentageService.getPercentage()).thenThrow(RuntimeException.class);
        when(this.dynamicPercentageService.getCachePercentage()).thenThrow(CacheNotFoundException.class);

        assertThrows(CacheNotFoundException.class,
                () -> this.calculatorService.calculate(num, num));

        verify(dynamicPercentageService, times(1)).getPercentage();
        verify(dynamicPercentageService, times(1)).getCachePercentage();
    }

    @Test
    void calculate_withValidParameters_withService() {
        Double num = Double.valueOf(5.0);
        Double result = Double.valueOf(11.0);

        when(this.dynamicPercentageService.getPercentage()).thenReturn(Double.valueOf(0.1));

        assertEquals(this.calculatorService.calculate(num, num), result);

        verify(dynamicPercentageService, times(1)).getPercentage();
        verify(dynamicPercentageService, times(0)).getCachePercentage();
    }

    @Test
    void calculate_withFirstNullParameter() {
        Double num = Double.valueOf(5.0);

        assertThrows(NullPointerException.class, () -> this.calculatorService.calculate(null, num));

        verify(dynamicPercentageService, times(0)).getPercentage();
        verify(dynamicPercentageService, times(0)).getCachePercentage();
    }

    @Test
    void calculate_withSecondNullParameter() {
        Double num = Double.valueOf(5.0);

        assertThrows(NullPointerException.class, () -> this.calculatorService.calculate(num, null));

        verify(dynamicPercentageService, times(0)).getPercentage();
        verify(dynamicPercentageService, times(0)).getCachePercentage();
    }

}