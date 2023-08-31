package com.mehedi.controller;

import com.mehedi.customExceptions.EmailValidationException;
import com.mehedi.customExceptions.NameValidationException;
import com.mehedi.customExceptions.InvalidIdValidationException;
import com.mehedi.customExceptions.NonIntegerIdException;
import com.mehedi.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        if(!isInteger(String.valueOf(user.getId()) )) {
            if(user.getId().length()>9) {
                throw new NonIntegerIdException("ID length is too long!");
            }
            throw new NonIntegerIdException("ID must be in integer!");
        }

        int id = Integer.parseInt(user.getId());
        if (id <= 0) {
            throw new InvalidIdValidationException("Invalid ID. ID should be greater than 0. Given ID: " + user.getId());
        }
        if(!isValidName(user.getFname())) {
            throw new NameValidationException("Not a valid Name :: " + user.getFname());
        }
        if(!isValidName(user.getLname())) {
            throw new NameValidationException("Not a valid Name :: " + user.getLname());
        }
        if(!user.getEmail().contains("@")) {
            throw new EmailValidationException("Not a valid Email :: " + user.getEmail());
        }
        return user;
    }

    private boolean isInteger(String value) {
        if(value.isEmpty() || value.length()>9) return false;
        for(int i=0;i<value.length();++i) {
            char ch = value.charAt(i);
            if(i==0 && ch=='-') continue;
            if(!(ch>='0' && ch<='9')) return false;
        }
        return true;
    }
    private boolean isValidName(String name) {
        if(name.isEmpty()) return false;
        for(int i=0;i<name.length();++i) {
            char ch = name.charAt(i);
            if(!Character.isAlphabetic(ch)) {
                return false;
            }
        }
        return true;
    }
}
