package com.auth.authenticationservice.exceptiion;

public class EmailAlreadyExists extends RuntimeException{
    public EmailAlreadyExists(String message) {
        super(message);
    }
}
