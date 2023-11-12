package com.spotify.userprofile.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus
public class IdAlreadyExists extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public IdAlreadyExists(String message) {
        super(message);
    }

    public IdAlreadyExists(String message, Throwable throwable) {
        super(message, throwable);
    }

}