package blue.rabbit.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class HttpRuntimeException extends RuntimeException {

    @Getter
    protected HttpStatus status = HttpStatus.FORBIDDEN;

    public HttpRuntimeException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
