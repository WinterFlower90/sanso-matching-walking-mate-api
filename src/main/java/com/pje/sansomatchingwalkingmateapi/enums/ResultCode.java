package com.pje.sansomatchingwalkingmateapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    SUCCESS(0,"성공하였습니다.")
    ,FAILED(-1, "실패하였습니다.")

    ,ACCESS_DENIED(-1000, "권한이 없습니다.")
    ,USERNAME_SIGN_IN_FAILED(-1001, "가입된 사용자가 아닙니다.")
    ,AUTHENTICATION_ENTRY_POINT(-1002, "접근 권한이 없습니다.")

    ,MISSING_DATA(-10000,"데이터를 찾을 수 없습니다.")
    ,WRONG_PHONE_NUMBER(-10001, "잘못된 핸드폰 번호입니다.")
    ,WRONG_USERNAME_TYPE(-10002, "잘못된 아이디 형식입니다.")
    ,WRONG_PASSWORD_MATCH(-10003, "비밀번호가 일치하지 않습니다.")
    ,ALREADY_DUPLICATE_ID(-10004, "중복된 아이디가 존재합니다.")

    ,NO_MEMBER_DATA(-20000, "회원정보가 없습니다.")
    , NICKNAME_USING(-20001, "닉네임이 사용중 입니다.")
    , NICKNAME_OVERLAP(-20002, "동일한 닉네임으로는 변경이 불가합니다.")

    ;


    private final Integer code;
    private final String msg;
}

