package cl.tenpo.rest.api.challenge.dtos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class HistoryRecordTest {
    @Test
    void testHistoryRecordGettersAndSetters() {
        Date date = new Date();
        String method = "POST";
        String endpoint = "/api/test";
        String params = "id=1";
        String requestBody = "{\"key\":\"value\"}";
        int responseStatus = 200;
        String responseBody = "{\"message\":\"success\"}";

        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setDate(date);
        historyRecord.setRequestMethod(method);
        historyRecord.setRequestEndpoint(endpoint);
        historyRecord.setRequestParams(params);
        historyRecord.setRequestBody(requestBody);
        historyRecord.setResponseStatus(responseStatus);
        historyRecord.setResponseBody(responseBody);

        assertEquals(date, historyRecord.getDate());
        assertEquals(method, historyRecord.getRequestMethod());
        assertEquals(endpoint, historyRecord.getRequestEndpoint());
        assertEquals(params, historyRecord.getRequestParams());
        assertEquals(requestBody, historyRecord.getRequestBody());
        assertEquals(responseStatus, historyRecord.getResponseStatus());
        assertEquals(responseBody, historyRecord.getResponseBody());
    }

    @Test
    void testHistoryRecordEqualsAndHashCode() {
        HistoryRecord record1 = new HistoryRecord()
                .date(new Date())
                .requestMethod("GET")
                .requestEndpoint("/api/resource")
                .requestParams("key=value")
                .requestBody("{}")
                .responseStatus(200)
                .responseBody("{\"message\":\"ok\"}");

        HistoryRecord record2 = new HistoryRecord()
                .date(record1.getDate())
                .requestMethod("GET")
                .requestEndpoint("/api/resource")
                .requestParams("key=value")
                .requestBody("{}")
                .responseStatus(200)
                .responseBody("{\"message\":\"ok\"}");

        assertEquals(record1, record2);
        assertEquals(record1.hashCode(), record2.hashCode());
    }

    @Test
    void testHistoryRecordEqualsSame() {
        HistoryRecord record1 = new HistoryRecord()
                .date(new Date())
                .requestMethod("GET")
                .requestEndpoint("/api/resource")
                .requestParams("key=value")
                .requestBody("{}")
                .responseStatus(200)
                .responseBody("{\"message\":\"ok\"}");

        assertEquals(record1, record1);
        assertEquals(record1.hashCode(), record1.hashCode());
    }
    @Test
    void testHistoryRecordEqualsNull() {
        HistoryRecord record1 = null;

        HistoryRecord record2 = new HistoryRecord()
                .date(new Date())
                .requestMethod("GET")
                .requestEndpoint("/api/resource")
                .requestParams("key=value")
                .requestBody("{}")
                .responseStatus(200)
                .responseBody("{\"message\":\"ok\"}");

        assertNotEquals(record2, record1);
    }

    @Test
    void testHistoryRecordToString() {
        HistoryRecord historyRecord = new HistoryRecord()
                .date(new Date())
                .requestMethod("DELETE")
                .requestEndpoint("/api/delete")
                .requestParams("id=5")
                .requestBody("{}")
                .responseStatus(204)
                .responseBody("");

        String toStringOutput = historyRecord.toString();
        assertTrue(toStringOutput.contains("requestMethod: DELETE"));
        assertTrue(toStringOutput.contains("requestEndpoint: /api/delete"));
        assertTrue(toStringOutput.contains("requestParams: id=5"));
        assertTrue(toStringOutput.contains("responseStatus: 204"));
    }
}