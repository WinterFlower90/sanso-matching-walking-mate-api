package com.pje.sansomatchingwalkingmateapi.exception;

public class CUserNameSignException extends RuntimeException {
    public CUserNameSignException(String msg, Throwable t) {
        super(msg, t);
    }

    public CUserNameSignException(String msg) {
        super(msg);
    }

    public CUserNameSignException() {
        super();
    }
}
