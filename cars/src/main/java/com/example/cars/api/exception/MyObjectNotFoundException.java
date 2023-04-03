package com.example.cars.api.exception;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MyObjectNotFoundException extends RuntimeException {
    public MyObjectNotFoundException(String message) {
        super(message);
    }

    public MyObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
