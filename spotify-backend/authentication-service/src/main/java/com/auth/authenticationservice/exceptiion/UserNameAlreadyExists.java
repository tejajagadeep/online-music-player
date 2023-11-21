package com.auth.authenticationservice.exceptiion;

public class UserNameAlreadyExists extends RuntimeException{
    public UserNameAlreadyExists(String message) {
        super(message);
    }
}
