package cl.tenpo.rest.api.challenge.controllers;

import cl.tenpo.rest.api.challenge.dtos.PaginatedHistory;
import cl.tenpo.rest.api.challenge.services.ApiCallLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired private ApiCallLogService apiCallLogService;

    @org.springframework.beans.factory.annotation.Autowired
    public HistoryApiController(ObjectMapper objectMapper, HttpServletRequest request,
                                ApiCallLogService apiCallLogService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.apiCallLogService = apiCallLogService;
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.ofNullable(objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    public ResponseEntity<PaginatedHistory> getHistory(
            @Parameter(in = ParameterIn.QUERY, description = "", schema = @Schema(defaultValue = "0")) @Valid
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @Parameter(in = ParameterIn.QUERY, description = "", schema = @Schema(defaultValue = "10")) @Valid
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<PaginatedHistory>(this.apiCallLogService.findAll(page, size), HttpStatus.OK);
        }

        return new ResponseEntity<PaginatedHistory>(HttpStatus.NOT_IMPLEMENTED);
    }

}
