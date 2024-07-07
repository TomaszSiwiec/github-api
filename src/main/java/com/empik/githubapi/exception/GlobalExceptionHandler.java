package com.empik.githubapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GitHubUserNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(GitHubUserNotFoundException ex, WebRequest request) {
        ExceptionResponseBody responseBody =
                ExceptionResponseBody.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .path(request.getDescription(false)
                                .substring(4))
                        .message(ex.getMessage())
                        .build();
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

}
