package com.pje.sansomatchingwalkingmateapi.exception;

public class CWrongUsernameType extends RuntimeException {
    public CWrongUsernameType(String msg, Throwable t) {
        super(msg, t);
    }

    public CWrongUsernameType(String msg) {
        super(msg);
    }

    public CWrongUsernameType() {
        super();
    }
}
