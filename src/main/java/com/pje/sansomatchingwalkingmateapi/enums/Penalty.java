package com.pje.sansomatchingwalkingmateapi.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Penalty {
    NONE("")
    , CAUTION("주의")
    , WARNING("경고")
    , BLACK_LIST("블랙 리스트")
    ;

    private final String name;
}

