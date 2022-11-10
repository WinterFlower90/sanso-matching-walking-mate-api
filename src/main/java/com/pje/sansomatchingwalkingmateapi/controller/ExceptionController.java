package com.pje.sansomatchingwalkingmateapi.controller;

import com.pje.sansomatchingwalkingmateapi.exception.CAccessDeniedException;
import com.pje.sansomatchingwalkingmateapi.exception.CAuthenticationEntryPointException;
import com.pje.sansomatchingwalkingmateapi.model.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {
    @ApiOperation(value = "가입 정보가 없는 사용자 예외처리")
    @GetMapping("/access-denied")
    public CommonResult accessDeniedException() {
        throw new CAccessDeniedException();
    }

    @ApiOperation(value = "권한에 맞지 않는 사용자 예외처리")
    @GetMapping("/entry-point")
    public CommonResult entryPointException() {
        throw new CAuthenticationEntryPointException();
    }
}
