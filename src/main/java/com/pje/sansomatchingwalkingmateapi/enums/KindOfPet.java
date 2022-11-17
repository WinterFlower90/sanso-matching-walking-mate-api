package com.pje.sansomatchingwalkingmateapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KindOfPet {

    DOG("강아지")
    , CAT("고양이")
    , RACCOON("라쿤")
    , ETC("그외");

    private final String name;
}
