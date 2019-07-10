package com.yasik.service.exception;

public class EmptyObjectException extends RuntimeException {

    public EmptyObjectException(String message) {
        super(message);
    }

    public EmptyObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyObjectException(Throwable cause) {
        super(cause);
    }
}
