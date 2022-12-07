package com.pje.sansomatchingwalkingmateapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NoticeIsEnable {
    NOTICE_ENABLE("공지중", true)
    ,NOTICE_DISABLE("해제중", false)
    ;

    private final String name;
    private final Boolean type;
}
