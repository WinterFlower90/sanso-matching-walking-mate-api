package com.pje.sansomatchingwalkingmateapi.controller;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.enums.MemberGroup;
import com.pje.sansomatchingwalkingmateapi.enums.MemberInformationType;
import com.pje.sansomatchingwalkingmateapi.model.common.CommonResult;
import com.pje.sansomatchingwalkingmateapi.model.common.ListResult;
import com.pje.sansomatchingwalkingmateapi.model.common.SingleResult;
import com.pje.sansomatchingwalkingmateapi.model.member.MemberCreateRequest;
import com.pje.sansomatchingwalkingmateapi.model.member.MemberInformationResponse;
import com.pje.sansomatchingwalkingmateapi.model.member.NickNameChangeRequest;
import com.pje.sansomatchingwalkingmateapi.model.member.NickNameResponse;
import com.pje.sansomatchingwalkingmateapi.service.MemberDataService;
import com.pje.sansomatchingwalkingmateapi.service.common.ProfileService;
import com.pje.sansomatchingwalkingmateapi.service.common.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class MemberController {
    private final MemberDataService memberDataService;
    private final ProfileService profileService;

    @ApiOperation(value = "[일반유저/관리자] 회원 등록")
    @PostMapping("/join")
    public CommonResult setMember(@RequestBody @Valid MemberCreateRequest createRequest) {
        memberDataService.setMember(MemberGroup.ROLE_USER, createRequest);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "[일반유저] 닉네임 조회")
    @GetMapping("/nickname")
    public SingleResult<NickNameResponse> getNickName() {
        Member member = profileService.getMemberData();
        return ResponseService.getSingleResult(memberDataService.getNickName(member));
    }
    @ApiOperation(value = "[일반유저] 닉네임 수정")
    @PutMapping("/nickname-change")
    public CommonResult putNickName(@RequestBody @Valid NickNameChangeRequest Request) {
        Member member= profileService.getMemberData();
        memberDataService.putNickName(member, Request);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(
            value = "[관리자] 회원정보 리스트 조회"
            , notes = "전체/평점순/블랙회원을 선택하고 페이지 값을 받아 조회한다."
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberInformationType", value = "전체/블랙/평점", required = true),
            @ApiImplicitParam(name = "pageNum", value = "페이지번호", required = true)
    })
    @GetMapping("/information/{pageNum}")
    public ListResult<MemberInformationResponse> getMembers(
            @RequestParam MemberInformationType memberInformationType,
            @PathVariable int pageNum
    ) {
        if (memberInformationType.equals(MemberInformationType.ALL)) {
            return ResponseService.getListResult(memberDataService.getMembers(pageNum), true);
        } else if (memberInformationType.equals(MemberInformationType.BLACK_LIST)) {
            return ResponseService.getListResult(memberDataService.getBlackMembers(pageNum), true);
        } else {
            return ResponseService.getListResult(memberDataService.getStarPointMembers(pageNum), true);
        }
    }

}
