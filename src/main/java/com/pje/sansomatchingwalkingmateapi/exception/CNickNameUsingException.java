package com.pje.sansomatchingwalkingmateapi.exception;

public class CNickNameUsingException extends RuntimeException {
    public CNickNameUsingException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNickNameUsingException(String msg) {
        super(msg);
    }

    public CNickNameUsingException() {
        super();
    }
}

