package com.phoenixgb6.portfolio.universitycrm.controller;


import com.phoenixgb6.portfolio.universitycrm.exception.BadRequestException;
import com.phoenixgb6.portfolio.universitycrm.exception.NotFoundException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalControllerAdvice {

    //InitBinder to trim all the incomming strings
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(false);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    //Exception handler for Not found - 404
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

    //Exception handler for Not found - 400
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
}
