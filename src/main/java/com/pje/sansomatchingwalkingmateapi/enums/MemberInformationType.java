package com.pje.sansomatchingwalkingmateapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberInformationType {
    ALL("전체")
    , BLACK_LIST("블랙회원")
    , STAR_POINT("별점순 정렬")
    ;

    private final String name;
}
