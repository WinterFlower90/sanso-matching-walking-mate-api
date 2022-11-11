package com.pje.sansomatchingwalkingmateapi.exception;

public class CWrongPhoneNumberException extends RuntimeException {
    public CWrongPhoneNumberException(String msg, Throwable t) {
        super(msg, t);
    }

    public CWrongPhoneNumberException(String msg) {
        super(msg);
    }

    public CWrongPhoneNumberException() {
        super();
    }
}
