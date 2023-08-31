package com.mehedi.controller;

import com.mehedi.customExceptions.EmailValidationException;
import com.mehedi.customExceptions.FirstNameValidationException;
import com.mehedi.customExceptions.InvalidIdValidationException;
import com.mehedi.customExceptions.NonIntegerIdException;
import com.mehedi.entity.User;
import com.mehedi.errors.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        if(!isInteger(String.valueOf(user.getId()) )) {
            throw new NonIntegerIdException("ID must be in integer!");
        }

        int id = Integer.parseInt(user.getId());
        if (id <= 0) {
            throw new InvalidIdValidationException("Invalid ID. ID should be greater than 0. Given ID: " + user.getId());
        }
        if(!user.getEmail().contains("@")) {
            throw new EmailValidationException("Not a valid Email :: " + user.getEmail());
        }
        if(user.getFname().contains("!")) {
            throw new FirstNameValidationException("Not a valid Name :: " + user.getFname());
        }
        return user;
    }

    private boolean isInteger(String value) {
        if(value.isEmpty()) return false;
        for(int i=0;i<value.length();++i) {
            char ch = value.charAt(i);
            if(i==0 && ch=='-') continue;
            if(!(ch>='0' && ch<='9')) return false;
        }
        return true;
    }

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
}
