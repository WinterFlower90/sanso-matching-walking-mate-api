package com.pje.sansomatchingwalkingmateapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberGroup {
    ROLE_ADMIN("최고관리자"),
    ROLE_USER("사용자")
    ;

    private final String name;
}
