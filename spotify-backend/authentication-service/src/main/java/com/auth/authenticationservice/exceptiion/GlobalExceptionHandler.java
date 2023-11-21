package com.auth.authenticationservice.exceptiion;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<ErrorResponse> EmailAlreadyExistsExceptionHandler(EmailAlreadyExists e){
        ErrorResponse response=new ErrorResponse();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNameAlreadyExists.class)
    public ResponseEntity<ErrorResponse> UserNamelAlreadyExistsExceptionHandler(UserNameAlreadyExists e){
        ErrorResponse response=new ErrorResponse();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidSalaryException.class)
    public ResponseEntity<ErrorResponse> InvalidSalaryExceptionHandler(InvalidSalaryException e){
        ErrorResponse response=new ErrorResponse();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> AuthenticationExceptionHandler(AuthenticationException e){
        ErrorResponse response=new ErrorResponse();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

}
