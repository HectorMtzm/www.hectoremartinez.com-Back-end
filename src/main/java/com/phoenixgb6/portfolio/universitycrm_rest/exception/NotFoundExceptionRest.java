package com.phoenixgb6.portfolio.universitycrm_rest.exception;

public class NotFoundExceptionRest extends RuntimeException{

    public NotFoundExceptionRest(String message) {
        super(message);
    }

    public NotFoundExceptionRest(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundExceptionRest(Throwable cause) {
        super(cause);
    }
}
