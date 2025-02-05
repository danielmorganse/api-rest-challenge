package cl.tenpo.rest.api.challenge.repositories;

import cl.tenpo.rest.api.challenge.entities.ApiCallRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiCallRecordRepository extends JpaRepository<ApiCallRecord, Long> {
}
