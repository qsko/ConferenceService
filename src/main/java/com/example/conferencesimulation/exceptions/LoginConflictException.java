package com.example.conferencesimulation.exceptions;

public class LoginConflictException extends RuntimeException{
    public LoginConflictException(String errorMessage) {
        super(errorMessage);
    }
}
