package cl.tenpo.rest.api.challenge.services;

import cl.tenpo.rest.api.challenge.dtos.PaginatedHistory;
import cl.tenpo.rest.api.challenge.entities.ApiCallRecord;
import cl.tenpo.rest.api.challenge.repositories.ApiCallRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiCallLogService {

    @Autowired
    private ApiCallRecordRepository apiCallRecordRepository;

    @Async
    public void save(ApiCallRecord entity) {
        this.apiCallRecordRepository.save(entity);
    }

    public PaginatedHistory findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        PaginatedHistory paginatedHistory = PaginatedHistory.builder()
                //.total(this.apiCallRecordRepository.count())
                //.records(this.apiCallRecordRepository.findAll(pageable).toList())
                .build();

        return paginatedHistory;
    }
}
