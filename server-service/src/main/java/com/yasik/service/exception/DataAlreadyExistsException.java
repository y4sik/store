package com.yasik.service.exception;

public class DataAlreadyExistsException extends RuntimeException {

    public DataAlreadyExistsException(String message) {
        super(message);
    }

    public DataAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAlreadyExistsException(Throwable cause) {
        super(cause);
    }

}
