package com.phoenixgb6.portfolio.universitycrm.controller;

import com.phoenixgb6.portfolio.universitycrm.exception.BadRequestException;
import com.phoenixgb6.portfolio.universitycrm.exception.NotFoundException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(basePackages = "com.phoenixgb6.portfolio.universitycrm")
public class MvcControllerAdvice {

    //InitBinder to trim all the incoming strings
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(false);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

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

    //Catches all other exceptions
    @ExceptionHandler(Exception.class)
    public ModelAndView badRequestHandler(Exception ex) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        mav.addObject("errorCode", 400);
        mav.addObject("message", "Your browser sent a request that this server could not understand");
        mav.addObject("project", "universitycrm");
        mav.addObject("type", null);
        return mav;
    }

}