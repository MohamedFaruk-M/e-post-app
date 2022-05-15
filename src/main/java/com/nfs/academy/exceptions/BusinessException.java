package com.nfs.academy.exceptions;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -9214152334867407118L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
