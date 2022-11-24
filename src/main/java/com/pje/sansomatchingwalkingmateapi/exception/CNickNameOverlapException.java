package com.pje.sansomatchingwalkingmateapi.exception;

public class CNickNameOverlapException extends RuntimeException {
    public CNickNameOverlapException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNickNameOverlapException(String msg) {
        super(msg);
    }

    public CNickNameOverlapException() {
        super();
    }
}
