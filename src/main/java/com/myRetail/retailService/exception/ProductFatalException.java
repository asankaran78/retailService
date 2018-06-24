package com.myRetail.retailService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ProductFatalException extends  RuntimeException{
    private static final long serialVersionUID = 1L;

    public ProductFatalException(String message, Throwable cause) {
        super(message, cause);

    }

    public ProductFatalException(String message) {
        super(message);
    }

    public ProductFatalException(Throwable cause) {
        super(cause);

    }


}


