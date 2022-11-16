package com.pje.sansomatchingwalkingmateapi.controller;

import com.pje.sansomatchingwalkingmateapi.enums.MemberGroup;
import com.pje.sansomatchingwalkingmateapi.model.common.SingleResult;
import com.pje.sansomatchingwalkingmateapi.model.member.LoginRequest;
import com.pje.sansomatchingwalkingmateapi.model.member.LoginResponse;
import com.pje.sansomatchingwalkingmateapi.service.common.LoginService;
import com.pje.sansomatchingwalkingmateapi.service.common.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "로그인")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/login")
public class LoginController {
    private final LoginService loginService;

    @ApiOperation(value = "웹 - 관리자 로그인")
    @PostMapping("/web/admin")
    public SingleResult<LoginResponse> doLoginAdmin(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseService.getSingleResult(loginService.doLogin(MemberGroup.ROLE_ADMIN, loginRequest, "WEB"));
    }

    @ApiOperation(value = "앱 - 일반유저 로그인")
    @PostMapping("/app/user")
    public SingleResult<LoginResponse> doLoginUser(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseService.getSingleResult(loginService.doLogin(MemberGroup.ROLE_USER, loginRequest, "APP"));
    }
}
