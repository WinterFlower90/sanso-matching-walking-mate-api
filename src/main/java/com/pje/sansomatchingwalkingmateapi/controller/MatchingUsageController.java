package com.pje.sansomatchingwalkingmateapi.controller;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.model.common.CommonResult;
import com.pje.sansomatchingwalkingmateapi.service.MatchingUsageService;
import com.pje.sansomatchingwalkingmateapi.service.common.ProfileService;
import com.pje.sansomatchingwalkingmateapi.service.common.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "매칭 내역")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/matching-usage")
public class MatchingUsageController {
    private final MatchingUsageService matchingUsageService;
    private final ProfileService profileService;

    @ApiOperation(value = "매칭 신청 등록하기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "receiveMemberId", value = "신청받을 회원 시퀀스", required = true)
    })
    @PostMapping("/new/{receiveMemberId}")
    public CommonResult setNotice(@PathVariable long receiveMemberId) {
        Member member = profileService.getMemberData();
        matchingUsageService.setMatchingApply(member, receiveMemberId);
        return ResponseService.getSuccessResult();
    }
}
