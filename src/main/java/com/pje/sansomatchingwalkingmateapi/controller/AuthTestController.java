package com.pje.sansomatchingwalkingmateapi.controller;

import com.pje.sansomatchingwalkingmateapi.model.common.CommonResult;
import com.pje.sansomatchingwalkingmateapi.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "권한 테스트")
@RestController
@RequestMapping("/v1/auth-test")
public class AuthTestController {
    @ApiOperation(value = "관리자만 접근 가능한 곳")
    @GetMapping("/test-admin")
    public CommonResult testAdmin() {
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "일반유저만 접근 가능한 곳")
    @GetMapping("/test-user")
    public CommonResult testUser() {
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "로그인 유저 모두 접근 가능한 곳")
    @GetMapping("/test-all")
    public CommonResult testAll() {
        return ResponseService.getSuccessResult();
    }
}
