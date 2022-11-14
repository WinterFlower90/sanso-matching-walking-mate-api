package com.pje.sansomatchingwalkingmateapi.exception;

public class CWrongPasswordMatch extends RuntimeException {
    public CWrongPasswordMatch(String msg, Throwable t) {
        super(msg, t);
    }

    public CWrongPasswordMatch(String msg) {
        super(msg);
    }

    public CWrongPasswordMatch() {
        super();
    }
}
