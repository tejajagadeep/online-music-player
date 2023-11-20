package com.shalem.authenticationservice.exceptiion;

public class InvalidSalaryException extends RuntimeException{
    public InvalidSalaryException(String message) {
        super(message);
    }
}
