package ru.levshin.stackoverflow.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;

@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonExceptionHandler.class);

    @ExceptionHandler(ResourceAccessException.class)
    protected <T extends ResourceAccessException> ResponseEntity<String> exceptionHandler(T exception) {
        LOGGER.error(exception.getMessage(), exception);
        return new ResponseEntity<>("The service is unavailable, try again later", HttpStatus.NOT_FOUND);
    }
}
