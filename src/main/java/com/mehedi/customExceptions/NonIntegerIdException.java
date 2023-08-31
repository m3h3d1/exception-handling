package com.mehedi.customExceptions;

public class NonIntegerIdException extends RuntimeException{
    public NonIntegerIdException(String error) {
        super(error);
    }
}
