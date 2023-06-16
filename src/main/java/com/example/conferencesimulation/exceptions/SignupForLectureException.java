package com.example.conferencesimulation.exceptions;

public class SignupForLectureException extends RuntimeException{
    public SignupForLectureException(String errorMessage) {
        super(errorMessage);
    }
}
