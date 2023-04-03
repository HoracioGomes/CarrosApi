package com.example.cars.api.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serializable;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({EmptyResultDataAccessException.class, NoSuchElementException.class})
    public ResponseEntity errorNotFound(Exception exception) {
        return ResponseEntity.notFound().build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity illegalArgument(Exception exception) {
        return ResponseEntity.badRequest().build();
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<>(new ExceptionError("Operação não permitida!"), HttpStatus.METHOD_NOT_ALLOWED);
    }

    class ExceptionError implements Serializable {
        private String message;

        public ExceptionError(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
