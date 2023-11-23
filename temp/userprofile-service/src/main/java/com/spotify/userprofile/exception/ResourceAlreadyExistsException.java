package com.spotify.userprofile.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

public class ResourceAlreadyExistsException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

//    public ResourceAlreadyExistsException(String message, Throwable throwable) {
//        super(message, throwable);
//    }

}