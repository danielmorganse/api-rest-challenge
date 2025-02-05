package cl.tenpo.rest.api.challenge.repositories;

import cl.tenpo.rest.api.challenge.entities.ApiCallRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApiCallRecordRepository extends JpaRepository<ApiCallRecord, Long>,
        PagingAndSortingRepository<ApiCallRecord, Long> {
}
