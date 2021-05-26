package com.example.restservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class TransactionException extends RuntimeException {
    public TransactionException(String response) {
        super(response);
    }
}
