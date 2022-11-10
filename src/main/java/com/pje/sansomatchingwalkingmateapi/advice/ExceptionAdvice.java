package com.pje.sansomatchingwalkingmateapi.advice;

import com.pje.sansomatchingwalkingmateapi.enums.ResultCode;
import com.pje.sansomatchingwalkingmateapi.exception.*;
import com.pje.sansomatchingwalkingmateapi.model.CommonResult;
import com.pje.sansomatchingwalkingmateapi.service.ResponseService;
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


}

