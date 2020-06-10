package com.phoenixgb6.portfolio.universitycrm_rest.controller;


import com.phoenixgb6.portfolio.universitycrm_rest.exception.AlreadyExistExceptionRest;
import com.phoenixgb6.portfolio.universitycrm_rest.exception.ErrorResponse;
import com.phoenixgb6.portfolio.universitycrm_rest.exception.NotFoundExceptionRest;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

//@Order(Ordered.HIGHEST_PRECEDENCE)
//@ControllerAdvice(basePackages = "com.phoenixgb6.portfolio.universitycrm_rest")
@ControllerAdvice
public class RestControllerAdvice {

    //InitBinder to trim all the incoming strings
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(false);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    //Exception handler for 404
    @ExceptionHandler({NotFoundExceptionRest.class, ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> notFoundHandler(Exception ex){

        ErrorResponse error = new ErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //Exception handler for 409
    @ExceptionHandler(AlreadyExistExceptionRest.class)
    public ResponseEntity<ErrorResponse> alreadyExistHandler(AlreadyExistExceptionRest ex){

        ErrorResponse error = new ErrorResponse();

        error.setStatus(HttpStatus.CONFLICT.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    //Catches all the other exceptions - 400
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> alreadyExistHandler(Exception ex){

        ErrorResponse error = new ErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
