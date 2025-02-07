package cl.tenpo.rest.api.challenge.services;

import cl.tenpo.rest.api.challenge.exceptions.CacheNotFoundException;
import cl.tenpo.rest.api.challenge.rest.clients.externalpercentage.ExternalPercentageClient;
import cl.tenpo.rest.api.challenge.rest.clients.externalpercentage.dtos.ResultExternalPercentage;
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
class DynamicPercentageServiceTest {

    @Mock
    ExternalPercentageClient externalPercentageClient;

    @InjectMocks
    DynamicPercentageService dynamicPercentageService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPercentage_errorFeignClient() {
        when(this.externalPercentageClient.getPercentage()).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> this.dynamicPercentageService.getPercentage());

        verify(this.externalPercentageClient, times(1)).getPercentage();
    }

    @Test
    void getPercentage_okFeignClient() {
        ResultExternalPercentage resultExternalPercentage = new ResultExternalPercentage(Double.valueOf(0.1));
        when(this.externalPercentageClient.getPercentage()).thenReturn(resultExternalPercentage);

        assertEquals(this.dynamicPercentageService.getPercentage(), resultExternalPercentage.getPercentage());

        verify(this.externalPercentageClient, times(1)).getPercentage();
    }

    @Test
    void getCachePercentage_noCache(){
        assertThrows(CacheNotFoundException.class, ()-> this.dynamicPercentageService.getCachePercentage());
    }

}