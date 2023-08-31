package com.mehedi.customExceptions;

public class EmailValidationException extends RuntimeException {
    public EmailValidationException(String error) {
        super(error);
    }
}
