package com.mehedi.customExceptions;

public class FirstNameValidationException extends RuntimeException {
    public FirstNameValidationException(String error) {
        super(error);
    }
}
