package com.phoenixgb6.portfolio.universitycrm_rest.exception;

public class AlreadyExistExceptionRest extends RuntimeException{
    public AlreadyExistExceptionRest(String message) {
        super(message);
    }

    public AlreadyExistExceptionRest(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistExceptionRest(Throwable cause) {
        super(cause);
    }
}
