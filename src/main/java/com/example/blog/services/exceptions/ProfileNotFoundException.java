package com.example.blog.services.exceptions;

public class ProfileNotFoundException extends ApplicationException {
    public ProfileNotFoundException(String message){
        super(message);
    }
}
