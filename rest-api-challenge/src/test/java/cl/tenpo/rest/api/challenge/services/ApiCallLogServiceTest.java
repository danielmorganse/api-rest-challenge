package cl.tenpo.rest.api.challenge.services;

import cl.tenpo.rest.api.challenge.dtos.PaginatedHistory;
import cl.tenpo.rest.api.challenge.entities.ApiCallRecord;
import cl.tenpo.rest.api.challenge.repositories.ApiCallRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class ApiCallLogServiceTest {

    @Mock
    private ApiCallRecordRepository apiCallRecordRepository;

    @InjectMocks
    ApiCallLogService apiCallLogService;

    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        when(this.apiCallRecordRepository.save(any(ApiCallRecord.class))).thenReturn(any(ApiCallRecord.class));

        this.apiCallLogService.save(new ApiCallRecord());

        verify(this.apiCallRecordRepository, times(1)).save(any(ApiCallRecord.class));
    }

    @Test
    void save_errorRepository() {
        ApiCallRecord entity = new ApiCallRecord();
        when(this.apiCallRecordRepository.save(any(ApiCallRecord.class))).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> this.apiCallLogService.save(entity));

        verify(this.apiCallRecordRepository, times(1)).save(any(ApiCallRecord.class));
    }

    @Test
    void findAll_errorRepositoryCount() {
        when(this.apiCallRecordRepository.count()).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> this.apiCallLogService.findAll(0, 10));

        verify(this.apiCallRecordRepository, times(1)).count();
        verify(this.apiCallRecordRepository, times(0)).findAll(any(Pageable.class));
    }

    @Test
    void findAll_errorRepositoryFindAll() {
        when(this.apiCallRecordRepository.count()).thenReturn(1L);
        when(this.apiCallRecordRepository.findAll(any(Pageable.class))).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> this.apiCallLogService.findAll(0, 10));

        verify(this.apiCallRecordRepository, times(1)).count();
        verify(this.apiCallRecordRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void findAll_repositoryCountZero() {
        when(this.apiCallRecordRepository.count()).thenReturn(0L);

        PaginatedHistory paginatedHistory = this.apiCallLogService.findAll(0, 10);

        assertEquals(0L, paginatedHistory.getTotal());
        assertTrue(paginatedHistory.getRecords().isEmpty());

        verify(this.apiCallRecordRepository, times(1)).count();
        verify(this.apiCallRecordRepository, times(0)).findAll(any(Pageable.class));
    }

    @Test
    void findAll_repositoryCountNonZero() {
        List<ApiCallRecord> listApiCallRecord = new ArrayList<>();
        ApiCallRecord apiCallRecord = new ApiCallRecord();
        listApiCallRecord.add(apiCallRecord);
        listApiCallRecord.add(apiCallRecord);
        listApiCallRecord.add(apiCallRecord);
        listApiCallRecord.add(apiCallRecord);
        listApiCallRecord.add(apiCallRecord);
        Page<ApiCallRecord> page = new PageImpl<>(listApiCallRecord);

        when(this.apiCallRecordRepository.count()).thenReturn(5L);
        when(this.apiCallRecordRepository.findAll(any(Pageable.class))).thenReturn(page);

        PaginatedHistory paginatedHistory = this.apiCallLogService.findAll(0, 10);

        assertEquals(5L, paginatedHistory.getTotal());
        assertFalse(paginatedHistory.getRecords().isEmpty());

        verify(this.apiCallRecordRepository, times(1)).count();
        verify(this.apiCallRecordRepository, times(1)).findAll(any(Pageable.class));
    }

}