package com.pje.sansomatchingwalkingmateapi.enums.keyword;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValuesTypeFriend {
    KIND ("친절한"),
    CUTE ("귀여운"),
    SHORT ("키가 작은"),
    TALL ("키가 큰"),
    SMILEY ("웃상"),
    DELICATE ("섬세한"),

    KEEPING_PROMISE ("약속을 잘 지키는"),
    KEEPING_LINE ("선을 잘 지키는"),
    RELATE_WELL ("공감을 잘하는"),

    SERIOUS_EXERCISE ("운동에 진심"),

    POSSIBLE_EXERCISE_COACH ("운동코치 가능한"),
    POSSIBLE_WEEKDAYS ("평일 가능한"),
    POSSIBLE_WEEKEND ("주말 가능한"),

    LIKE_PET ("동물을 좋아하는"),
    LIKE_SNACK ("간식을 좋아하는"),
    LIKE_COFFEE ("커피를 좋아하는"),
    ;
    private final String name;
}
