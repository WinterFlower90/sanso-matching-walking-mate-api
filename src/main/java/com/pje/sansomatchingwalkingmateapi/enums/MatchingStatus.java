package com.pje.sansomatchingwalkingmateapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MatchingStatus {
    STAND_BY ("매칭대기중"),
    MATCHING_DONE_NO_MEET ("매칭완료 : 만나기전"),
    MATCHING_DONE_MEET ("매칭완료 : 만남"),
    REFUSAL ("매칭거절"),

    ;
    private final String name;
}
