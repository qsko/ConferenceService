package com.example.conferencesimulation.controllerAdvice;

import com.example.conferencesimulation.exceptions.LoginConflictException;
import com.example.conferencesimulation.exceptions.SignupForLectureException;
import com.example.conferencesimulation.exceptions.StatisticsException;
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

    @ExceptionHandler(value = { NoSuchElementException.class})
    protected ResponseEntity<Object> handleNotFound (RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


    @ExceptionHandler(value = { LoginConflictException.class,
            SignupForLectureException.class, StatisticsException.class,})
    protected ResponseEntity<Object> handleConflict (RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
