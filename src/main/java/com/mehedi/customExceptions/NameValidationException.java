package com.mehedi.customExceptions;

public class NameValidationException extends RuntimeException {
    public NameValidationException(String error) {
        super(error);
    }
}
