package com.pje.sansomatchingwalkingmateapi.enums.keyword;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValuesTypeWalking {
    ONE_A_WEEK ("주 1회"),
    TWO_A_WEEK ("주 2회"),
    THREE_A_WEEK ("주 3회"),

    LIKE_MORNING ("오전 산책 선호"),
    LIKE_AFTERNOON ("오후 산책 선호"),
    LIKE_EVENING ("저녁 산책 선호"),
    LIKE_NEAR ("근거리 선호"),
    LIKE_LONG_DISTANCE ("장거리 선호"),

    POSSIBLE_SUDDEN_MEET("급만남 OK"),
    POSSIBLE_LONG_DISTANCE ("장거리 가능"),
    POSSIBLE_HIKING ("등산 가능"),

    SLOW_WALK ("천천히 걸어요"),
    FAST_WALK ("빨리 걸어")
    ;
    private final String name;
}
