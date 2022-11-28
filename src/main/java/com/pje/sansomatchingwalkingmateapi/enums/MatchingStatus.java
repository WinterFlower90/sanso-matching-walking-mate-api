package com.pje.sansomatchingwalkingmateapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MatchingStatus {
    WAIT ("대기중"),
    MATCHING_BEFORE_REAL_MEETING ("매칭 : 만나기전"),
    MATCHING_AFTER_REAL_MEETING ("매칭 : 만남"),
    NO_MEETING ("거절"),

    ;
    private final String name;
}
