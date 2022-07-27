package blue.rabbit.exceptions;

import blue.rabbit.dtos.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class HttpRuntimeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = HttpRuntimeException.class)
    protected ResponseEntity<Object> handleHttpRuntimeException(HttpRuntimeException ex, WebRequest request) {
        log.info("handle HttpRuntimeException.class");
        return handleExceptionInternal(ex, new ErrorDTO(ex.getStatus().value(), ex.getMessage()),
                new HttpHeaders(), ex.getStatus(), request);
    }
}