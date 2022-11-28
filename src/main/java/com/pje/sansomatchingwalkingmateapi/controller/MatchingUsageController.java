package com.pje.sansomatchingwalkingmateapi.controller;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.entity.WalkingAddress;
import com.pje.sansomatchingwalkingmateapi.model.common.CommonResult;
import com.pje.sansomatchingwalkingmateapi.model.common.ListResult;
import com.pje.sansomatchingwalkingmateapi.model.matchingUsage.MatchingCreateRequest;
import com.pje.sansomatchingwalkingmateapi.model.matchingUsage.MyMatchingAcceptRequest;
import com.pje.sansomatchingwalkingmateapi.model.matchingUsage.MyMatchingListResponse;
import com.pje.sansomatchingwalkingmateapi.service.MatchingUsageService;
import com.pje.sansomatchingwalkingmateapi.service.MemberDataService;
import com.pje.sansomatchingwalkingmateapi.service.WalkingAddressService;
import com.pje.sansomatchingwalkingmateapi.service.common.ProfileService;
import com.pje.sansomatchingwalkingmateapi.service.common.ResponseService;
import io.swagger.annotations.Api;
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
    private final MemberDataService memberDataService;
    private final WalkingAddressService walkingAddressService;


    @ApiOperation(value = "[일반유저] 매칭 신청")
    @PostMapping("/new")
    public CommonResult setMatching(@RequestBody @Valid MatchingCreateRequest request) {
        Member applyMember = profileService.getMemberData();
        Member receiveMember = memberDataService.getMember(request.getReceiveMemberId());
        WalkingAddress walkingAddress = walkingAddressService.getWalkingAddress(request.getWalkingAddressId());
        matchingUsageService.setMatching(applyMember, receiveMember, walkingAddress);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "[일반유저] 내가 신청한 매칭내역")
    @GetMapping("/my-apply")
    public ListResult<MyMatchingListResponse> getMyMatchingApplyList() {
        Member Member = profileService.getMemberData();
        return ResponseService.getListResult(matchingUsageService.getMyMatchingApplyList(Member),true);
    }
    @ApiOperation(value = "[일반유저] 내가 받은 매칭내역")
    @GetMapping("/my-receive")
    public ListResult<MyMatchingListResponse> getMyMatchingReceiveList() {
        Member Member = profileService.getMemberData();
        return ResponseService.getListResult(matchingUsageService.getMyMatchingReceiveList(Member),true);
    }
    @ApiOperation(value = "[일반유저] 나의 매칭 수락")
    @PutMapping("/my-accept")
    public CommonResult putMyMatchingAccept(@RequestBody @Valid MyMatchingAcceptRequest request) {
        matchingUsageService.putMyMatchingAccept(request);
        return ResponseService.getSuccessResult();
    }

//    @ApiOperation(value = "[일반유저] 내가 매칭 신청한 내역 리스트 가져오기")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageNum", value = "페이지 숫자", required = true),
//    })
//    @PostMapping("/apply-list/page/{pageNum}")
//    public ListResult<MatchingListItem> getMyApplyMatchingList(@PathVariable int pageNum) {
//        Member member = profileService.getMemberData();
//        return ResponseService.getListResult(matchingUsageService.getMyApplyMatchingList(pageNum, member), true);
//    }
//
//    @ApiOperation(value = "[일반유저] 내가 받은 매칭 신청내역 리스트 가져오기")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageNum", value = "페이지 숫자", required = true),
//    })
//    @PostMapping("/receive-list/page/{pageNum}")
//    public ListResult<MatchingListItem> getMyReceiveMatchingList(@PathVariable int pageNum) {
//        Member member = profileService.getMemberData();
//        return ResponseService.getListResult(matchingUsageService.getMyReceiveMatchingList(pageNum, member), true);
//    }

}
