package cl.tenpo.rest.api.challenge.services;

import cl.tenpo.rest.api.challenge.entities.ApiCallRecord;
import cl.tenpo.rest.api.challenge.repositories.ApiCallRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiCallLogService {

    @Autowired
    private ApiCallRecordRepository apiCallRecordRepository;

    public ApiCallRecord save(ApiCallRecord entity){
        return this.apiCallRecordRepository.save(entity);
    }
}
