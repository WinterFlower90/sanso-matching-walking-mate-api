package com.pje.sansomatchingwalkingmateapi.enums.keyword;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValuesTypePet {
    UGLY ("못생겼어요"),
    CUTE ("귀여워요"),

    VERY_CUTE ("짱 귀여워요"),
    VERY_SHY ("낯을 많이 가려요"),
    VERY_WARINESS ("경계심이 많아요"),

    LIKE_SNACK ("간식을 좋아해요"),
    LIKE_PEOPLE ("사람을 좋아해요"),
    LIKE_PARK ("공원을 좋아해요"),
    LIKE_DOG ("강아지를 좋아해요"),

    NONE ("선택안함")
    ;
    private final String name;
}
