package cl.tenpo.rest.api.challenge.controllers.handlers;

import cl.tenpo.rest.api.challenge.dtos.Error;
import cl.tenpo.rest.api.challenge.exceptions.CacheNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.math.BigDecimal;

@ControllerAdvice
public class CalculatorExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {CacheNotFoundException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        Error error = new Error();
        error.setCode(BigDecimal.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
