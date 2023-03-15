package ru.levshin.stackoverflow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(RestClientException.class)
    protected <T extends RestClientException> ResponseEntity<String> exceptionHandler(T exception) {
        return new ResponseEntity<>("Неверные параметры запроса", HttpStatus.BAD_REQUEST);
    }
}
