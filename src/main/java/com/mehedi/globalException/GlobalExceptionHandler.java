package com.mehedi.globalException;

import com.mehedi.customExceptions.EmailValidationException;
import com.mehedi.customExceptions.InvalidIdValidationException;
import com.mehedi.customExceptions.NameValidationException;
import com.mehedi.customExceptions.NonIntegerIdException;
import com.mehedi.errors.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NonIntegerIdException.class)
    public ResponseEntity<Error> nonIntegerIdValidation(NonIntegerIdException exception) {
        Error error = new Error(1001, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidIdValidationException.class)
    public ResponseEntity<Error> invalidIdValidation(InvalidIdValidationException exception) {
        Error error = new Error(1002, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = NameValidationException.class)
    public ResponseEntity<Error> firstNameValidation(NameValidationException exception) {
        Error error = new Error(1003, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmailValidationException.class)
    public ResponseEntity<Error> emailValidation(EmailValidationException exception) {
        Error error = new Error(1004, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}