package com.pje.sansomatchingwalkingmateapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DropBoxDir {
    NOTICE("공지사항", "/erp/notice/");

    private final String name;
    private final String basePath;
}
