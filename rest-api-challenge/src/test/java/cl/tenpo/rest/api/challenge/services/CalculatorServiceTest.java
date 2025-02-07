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

import static org.junit.jupiter.api.Assertions.*;
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
    void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void calculate_withNullsParameters() throws Exception {
        assertThrows(NullPointerException.class, () -> calculatorService.calculate(null, null));

        verify(dynamicPercentageService, times(0)).getPercentage();
        verify(dynamicPercentageService, times(0)).getCachePercentage();
    }

    @Test
    void calculate_withNullsParameters_withNoCache() throws Exception {
        assertThrows(NullPointerException.class, () -> this.calculatorService.calculate(null, null));

        verify(dynamicPercentageService, times(0)).getPercentage();
        verify(dynamicPercentageService, times(0)).getCachePercentage();
    }

    @Test
    void calculate_withNullsParameters_withCache() throws Exception {
        assertThrows(NullPointerException.class, () -> this.calculatorService.calculate(null, null));

        verify(dynamicPercentageService, times(0)).getPercentage();
        verify(dynamicPercentageService, times(0)).getCachePercentage();
    }

    @Test
    void calculate_withValidParameters_withNoService_cache() throws Exception {
        when(this.dynamicPercentageService.getPercentage()).thenThrow(RuntimeException.class);
        when(this.dynamicPercentageService.getCachePercentage()).thenReturn(Double.valueOf(0.1));

        assertEquals(this.calculatorService.calculate(Double.valueOf(5.0), Double.valueOf(5.0)), Double.valueOf(11.0));

        verify(dynamicPercentageService, times(1)).getPercentage();
        verify(dynamicPercentageService, times(1)).getCachePercentage();
    }

    @Test
    void calculate_withValidParameters_withNoService_noCache() throws Exception {
        when(this.dynamicPercentageService.getPercentage()).thenThrow(RuntimeException.class);
        when(this.dynamicPercentageService.getCachePercentage()).thenThrow(CacheNotFoundException.class);

        assertThrows(CacheNotFoundException.class,
                () -> this.calculatorService.calculate(Double.valueOf(5.0), Double.valueOf(5.0)));

        verify(dynamicPercentageService, times(1)).getPercentage();
        verify(dynamicPercentageService, times(1)).getCachePercentage();
    }

    @Test
    void calculate_withValidParameters_withService() throws Exception {
        when(this.dynamicPercentageService.getPercentage()).thenReturn(Double.valueOf(0.1));

        assertEquals(this.calculatorService.calculate(Double.valueOf(5.0), Double.valueOf(5.0)), Double.valueOf(11.0));

        verify(dynamicPercentageService, times(1)).getPercentage();
        verify(dynamicPercentageService, times(0)).getCachePercentage();
    }

    @Test
    void calculate_withFirstNullParameter() throws Exception {
        assertThrows(NullPointerException.class, () -> this.calculatorService.calculate(null, Double.valueOf(5.0)));

        verify(dynamicPercentageService, times(0)).getPercentage();
        verify(dynamicPercentageService, times(0)).getCachePercentage();
    }

    @Test
    void calculate_withSecondNullParameter() throws Exception {
        assertThrows(NullPointerException.class, () -> this.calculatorService.calculate(Double.valueOf(5.0), null));

        verify(dynamicPercentageService, times(0)).getPercentage();
        verify(dynamicPercentageService, times(0)).getCachePercentage();
    }

}