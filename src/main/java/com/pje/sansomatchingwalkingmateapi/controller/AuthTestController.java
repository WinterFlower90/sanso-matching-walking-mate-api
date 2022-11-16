package com.pje.sansomatchingwalkingmateapi.controller;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.model.common.CommonResult;
import com.pje.sansomatchingwalkingmateapi.model.common.SingleResult;
import com.pje.sansomatchingwalkingmateapi.model.member.ProfileResponse;
import com.pje.sansomatchingwalkingmateapi.service.common.ProfileService;
import com.pje.sansomatchingwalkingmateapi.service.common.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "권한 테스트")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth-test")
public class AuthTestController {

    private final ProfileService profileService;

    @ApiOperation(value = "프로필 정보 가져오기")
    @GetMapping("/login-all/profile")
    public SingleResult<ProfileResponse> getProfile() {
        return ResponseService.getSingleResult(profileService.getProfile());
    }

    @ApiOperation(value = "토큰으로 member entity 가져오기")
    @GetMapping("/login-all/test-entity-info")
    public CommonResult getMemberData() {
        Member member = profileService.getMemberData();
        return ResponseService.getSuccessResult();
    }

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
