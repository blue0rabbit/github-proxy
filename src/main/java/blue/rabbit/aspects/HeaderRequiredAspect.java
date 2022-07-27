package blue.rabbit.aspects;

import blue.rabbit.exceptions.HttpRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class HeaderRequiredAspect {

    @Before(value = "@within(blue.rabbit.aspects.HeaderRequired) || @annotation(blue.rabbit.aspects.HeaderRequired)")
    public void before() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        if (request.getHeader("Accept") == null ||
        request.getHeader("Accept").equals("application/xml")) {
            throw new HttpRuntimeException(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
