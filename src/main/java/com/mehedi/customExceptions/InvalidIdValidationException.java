package com.mehedi.customExceptions;

public class InvalidIdValidationException extends RuntimeException {
    public InvalidIdValidationException(String error) {
        super(error);
    }
}
