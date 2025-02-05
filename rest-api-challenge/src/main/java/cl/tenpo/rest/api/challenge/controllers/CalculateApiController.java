package cl.tenpo.rest.api.challenge.controllers;

import cl.tenpo.rest.api.challenge.dtos.CalculateBody;
import cl.tenpo.rest.api.challenge.dtos.CalculateResult;
import cl.tenpo.rest.api.challenge.dtos.Error;
import cl.tenpo.rest.api.challenge.services.CalculatorService;
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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;


@RestController
public class CalculateApiController implements CalculateApi {

    private static final Logger log = LoggerFactory.getLogger(CalculateApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private CalculatorService calculatorService;

    @org.springframework.beans.factory.annotation.Autowired
    public CalculateApiController(ObjectMapper objectMapper, HttpServletRequest request,
                                  CalculatorService calculatorService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.calculatorService = calculatorService;
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.ofNullable(objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    public ResponseEntity<CalculateResult> calculateSumWithPercentage(
            @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid
            @RequestBody CalculateBody body
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Double result = this.calculatorService.calculate(body.getNum1(), body.getNum2());
            CalculateResult calculateResult = new CalculateResult();
            calculateResult.setResult(result);
            return new ResponseEntity<CalculateResult>(calculateResult, HttpStatus.OK);
        }
        return new ResponseEntity<CalculateResult>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        Error error = new Error();
        error.setCode(BigDecimal.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        error.setMessage("Error interno del servidor");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
