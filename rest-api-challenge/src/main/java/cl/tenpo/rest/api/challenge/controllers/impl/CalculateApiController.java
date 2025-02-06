package cl.tenpo.rest.api.challenge.controllers.impl;

import cl.tenpo.rest.api.challenge.controllers.CalculateApi;
import cl.tenpo.rest.api.challenge.dtos.CalculateBody;
import cl.tenpo.rest.api.challenge.dtos.CalculateResult;
import cl.tenpo.rest.api.challenge.dtos.Error;
import cl.tenpo.rest.api.challenge.exceptions.CacheNotFoundException;
import cl.tenpo.rest.api.challenge.services.CalculatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.math.BigDecimal;
import java.util.Optional;


@RestController
public class CalculateApiController implements CalculateApi {

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private CalculatorService calculatorService;

    @Autowired
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

    @Override
    public ResponseEntity<CalculateResult> calculateSumWithPercentage(
            @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid
            @RequestBody CalculateBody body
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Double result = this.calculatorService.calculate(body.getNum1(), body.getNum2());
            CalculateResult calculateResult = new CalculateResult();
            calculateResult.setResult(result);
            return new ResponseEntity<>(calculateResult, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleAll(
            RuntimeException ex, WebRequest request) {
        Error error = new Error();
        error.setCode(BigDecimal.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        error.setMessage("Error interno del servidor");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value
            = {CacheNotFoundException.class})
    protected ResponseEntity<Object> handleCacheNotFound(
            RuntimeException ex, WebRequest request) {
        Error error = new Error();
        error.setCode(BigDecimal.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
