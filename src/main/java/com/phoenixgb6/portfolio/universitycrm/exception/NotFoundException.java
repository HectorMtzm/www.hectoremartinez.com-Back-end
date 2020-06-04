package com.phoenixgb6.portfolio.universitycrm.exception;

import org.springframework.ui.Model;

public class NotFoundException extends RuntimeException {

    final int errorCode = 404;
    private String message;
    private Model model;

    public NotFoundException(String message, Model model) {
        this.message = message;
        this.model = model;
    }

    public NotFoundException(String message) {
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
