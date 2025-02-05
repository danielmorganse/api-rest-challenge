package cl.tenpo.rest.api.challenge.services;

import cl.tenpo.rest.api.challenge.dtos.HistoryRecord;
import cl.tenpo.rest.api.challenge.dtos.PaginatedHistory;
import cl.tenpo.rest.api.challenge.entities.ApiCallRecord;
import cl.tenpo.rest.api.challenge.repositories.ApiCallRecordRepository;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    @Async
    public void save(ApiCallRecord entity) {
        this.apiCallRecordRepository.save(entity);
    }

    public PaginatedHistory findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        long count = this.apiCallRecordRepository.count();
        List<ApiCallRecord> list = this.apiCallRecordRepository.findAll(pageable).toList();
        List<HistoryRecord> list2 =
                list.stream().map(apiCallRecord -> modelMapper.map(apiCallRecord, HistoryRecord.class)).toList();

        PaginatedHistory paginatedHistory = PaginatedHistory.builder()
                .total(count)
                .records(list2)
                .build();

        return paginatedHistory;
    }


}
