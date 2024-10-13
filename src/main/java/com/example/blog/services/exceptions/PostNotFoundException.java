package com.example.blog.services.exceptions;

public class PostNotFoundException extends ApplicationException {
    public PostNotFoundException(String message){
        super(message);
    }
}
