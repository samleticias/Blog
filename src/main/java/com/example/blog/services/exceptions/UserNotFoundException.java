package com.example.blog.services.exceptions;

public class UserNotFoundException extends ApplicationException {
    public UserNotFoundException(String message){
        super(message);
    }
}
