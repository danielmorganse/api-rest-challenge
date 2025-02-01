package cl.tenpo.rest.api.challenge.controllers;

import cl.tenpo.rest.api.challenge.dtos.PaginatedHistory;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;


@RestController
public class HistoryApiController implements HistoryApi {

    private static final Logger log = LoggerFactory.getLogger(HistoryApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public HistoryApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.ofNullable(objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    public ResponseEntity<PaginatedHistory> getHistory(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema( defaultValue="0")) @Valid @RequestParam(value = "page", required = false, defaultValue="0") Integer page
,@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema( defaultValue="10")) @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PaginatedHistory>(objectMapper.readValue("{\n  \"total\" : 0,\n  \"records\" : [ {\n    \"endpoint\" : \"endpoint\",\n    \"requestBody\" : \"requestBody\",\n    \"response\" : \"response\",\n    \"urlParameters\" : {\n      \"key\" : \"urlParameters\"\n    },\n    \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"\n  }, {\n    \"endpoint\" : \"endpoint\",\n    \"requestBody\" : \"requestBody\",\n    \"response\" : \"response\",\n    \"urlParameters\" : {\n      \"key\" : \"urlParameters\"\n    },\n    \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"\n  } ]\n}", PaginatedHistory.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PaginatedHistory>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PaginatedHistory>(HttpStatus.NOT_IMPLEMENTED);
    }

}
