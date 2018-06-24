package com.myRetail.retailService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1100977312052181493L;

    public NotFoundException(String message) {
        super(message);
    }
    
}
