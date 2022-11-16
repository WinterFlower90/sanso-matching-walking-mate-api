package com.pje.sansomatchingwalkingmateapi.advice;

import com.pje.sansomatchingwalkingmateapi.enums.ResultCode;
import com.pje.sansomatchingwalkingmateapi.exception.*;
import com.pje.sansomatchingwalkingmateapi.model.common.CommonResult;
import com.pje.sansomatchingwalkingmateapi.service.common.ResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        return ResponseService.getFailResult(ResultCode.FAILED);
    }

    @ExceptionHandler(CAccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) //403 에러로 - 권한문제
    protected CommonResult customException(HttpServletRequest request, CAccessDeniedException e) {
        return ResponseService.getFailResult(ResultCode.ACCESS_DENIED);
    }

    @ExceptionHandler(CAuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) //권한문제
    protected CommonResult customException(HttpServletRequest request, CAuthenticationEntryPointException e) {
        return ResponseService.getFailResult(ResultCode.AUTHENTICATION_ENTRY_POINT);
    }

    @ExceptionHandler(CMissingDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CMissingDataException e) {
        return ResponseService.getFailResult(ResultCode.MISSING_DATA);
    }

    @ExceptionHandler(CNoMemberDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CNoMemberDataException e) {
        return ResponseService.getFailResult(ResultCode.NO_MEMBER_DATA);
    }

    @ExceptionHandler(CUserNameSignException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CUserNameSignException e) {
        return ResponseService.getFailResult(ResultCode.USERNAME_SIGN_IN_FAILED);
    }

    @ExceptionHandler(CWrongPhoneNumberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CWrongPhoneNumberException e) {
        return ResponseService.getFailResult(ResultCode.WRONG_PHONE_NUMBER);
    }

    @ExceptionHandler(CAlreadyDuplicateId.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CAlreadyDuplicateId e) {
        return ResponseService.getFailResult(ResultCode.ALREADY_DUPLICATE_ID);
    }

    @ExceptionHandler(CWrongPasswordMatch.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CWrongPasswordMatch e) {
        return ResponseService.getFailResult(ResultCode.WRONG_PASSWORD_MATCH);
    }

    @ExceptionHandler(CWrongUsernameType.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CWrongUsernameType e) {
        return ResponseService.getFailResult(ResultCode.WRONG_USERNAME_TYPE);
    }

}

