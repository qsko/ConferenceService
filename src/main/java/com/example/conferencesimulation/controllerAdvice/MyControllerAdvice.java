package com.example.conferencesimulation.controllerAdvice;

import com.example.conferencesimulation.exceptions.LoginConflictException;
import com.example.conferencesimulation.exceptions.SignupForLectureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { LoginConflictException.class, NoSuchElementException.class, SignupForLectureException.class})
    protected ResponseEntity<Object> handleConflict (RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        HttpStatus status;

        if(ex instanceof LoginConflictException || ex instanceof SignupForLectureException)
            status = HttpStatus.CONFLICT;
        else
            status = HttpStatus.NOT_FOUND;

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), status, request);
    }
}
