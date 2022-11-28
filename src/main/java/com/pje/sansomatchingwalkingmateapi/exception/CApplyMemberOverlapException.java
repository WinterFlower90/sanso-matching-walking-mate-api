package com.pje.sansomatchingwalkingmateapi.exception;

public class CApplyMemberOverlapException extends RuntimeException {
    public CApplyMemberOverlapException(String msg, Throwable t) {
        super(msg, t);
    }

    public CApplyMemberOverlapException(String msg) {
        super(msg);
    }

    public CApplyMemberOverlapException() {
        super();
    }
}

