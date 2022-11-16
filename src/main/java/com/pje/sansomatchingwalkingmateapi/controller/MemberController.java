package com.pje.sansomatchingwalkingmateapi.controller;

import com.pje.sansomatchingwalkingmateapi.enums.MemberGroup;
import com.pje.sansomatchingwalkingmateapi.model.common.CommonResult;
import com.pje.sansomatchingwalkingmateapi.model.member.MemberCreateRequest;
import com.pje.sansomatchingwalkingmateapi.service.MemberDataService;
import com.pje.sansomatchingwalkingmateapi.service.common.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class MemberController {
    private final MemberDataService memberDataService;

    @ApiOperation(value = "[일반유저/관리자] 회원 등록")
    @PostMapping("/join")
    public CommonResult setMember(@RequestBody @Valid MemberCreateRequest createRequest) {
        memberDataService.setMember(MemberGroup.ROLE_USER, createRequest);
        return ResponseService.getSuccessResult();
    }

}
