package com.example.blog.services.exceptions;

public class LikeNotFoundException extends ApplicationException {
    public LikeNotFoundException(String message){
        super(message);
    }
}
