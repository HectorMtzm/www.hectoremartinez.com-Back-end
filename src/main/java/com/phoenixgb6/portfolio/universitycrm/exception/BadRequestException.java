package com.phoenixgb6.portfolio.universitycrm.exception;

import org.springframework.ui.Model;

public class BadRequestException extends RuntimeException {

    final int errorCode = 400;
    private String message;
    private Model model;

    public BadRequestException(String message, Model model) {
        this.message = message;
        this.model = model;
    }

    public BadRequestException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Model getModel() {
        return model;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
