package com.pje.sansomatchingwalkingmateapi.exception;

public class CAlreadyDuplicateId extends RuntimeException {
    public CAlreadyDuplicateId(String msg, Throwable t) {
        super(msg, t);
    }

    public CAlreadyDuplicateId(String msg) {
        super(msg);
    }

    public CAlreadyDuplicateId() {
        super();
    }
}
