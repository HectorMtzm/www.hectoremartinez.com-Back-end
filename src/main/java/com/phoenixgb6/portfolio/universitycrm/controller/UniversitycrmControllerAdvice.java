package com.phoenixgb6.portfolio.universitycrm.controller;


import com.phoenixgb6.portfolio.universitycrm.exception.BadRequestException;
import com.phoenixgb6.portfolio.universitycrm.exception.NotFoundException;
import com.phoenixgb6.portfolio.universitycrm_rest.exception.AlreadyExistExceptionRest;
import com.phoenixgb6.portfolio.universitycrm_rest.exception.ErrorResponse;
import com.phoenixgb6.portfolio.universitycrm_rest.exception.NotFoundExceptionRest;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(basePackages = {"com.phoenixgb6.portfolio.universitycrm", "com.phoenixgb6.portfolio.universitycrm_rest"})
public class UniversitycrmControllerAdvice {

    //InitBinder to trim all the incoming strings
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(false);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    //
    // MVC exception handlers
    //

    //Exception handler for 404
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFoundHandler(NotFoundException ex) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        mav.addObject("errorCode", ex.getErrorCode());
        mav.addObject("message", ex.getMessage());
        mav.addObject("project", ex.getModel().getAttribute("project"));
        mav.addObject("type", ex.getModel().getAttribute("type"));
        return mav;
    }

    //Exception handler for 400
    @ExceptionHandler(BadRequestException.class)
    public ModelAndView badRequestHandler(BadRequestException ex) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        mav.addObject("errorCode", ex.getErrorCode());
        mav.addObject("message", ex.getMessage());
        mav.addObject("project", ex.getModel().getAttribute("project"));
        mav.addObject("type", ex.getModel().getAttribute("type"));
        return mav;
    }


    //
    //Rest exception handlers
    //

    //Exception handler for 404
    @ExceptionHandler(NotFoundExceptionRest.class)
    public ResponseEntity<ErrorResponse> notFoundHandler(NotFoundExceptionRest ex){

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

    // Catches all the other exceptions - 400
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> alreadyExistHandler(Exception ex){
        ErrorResponse error = new ErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
