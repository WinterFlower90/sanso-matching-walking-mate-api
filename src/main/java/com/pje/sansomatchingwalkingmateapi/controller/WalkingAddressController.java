package com.pje.sansomatchingwalkingmateapi.controller;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.model.common.CommonResult;
import com.pje.sansomatchingwalkingmateapi.model.common.ListResult;
import com.pje.sansomatchingwalkingmateapi.model.walkingAddress.WalkingAddressAdminRequest;
import com.pje.sansomatchingwalkingmateapi.model.walkingAddress.WalkingAddressUserFavoritesRequest;
import com.pje.sansomatchingwalkingmateapi.model.walkingAddress.WalkingAddressUserFavoritesResponse;
import com.pje.sansomatchingwalkingmateapi.service.MemberDataService;
import com.pje.sansomatchingwalkingmateapi.service.WalkingAddressService;
import com.pje.sansomatchingwalkingmateapi.service.common.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "산책 장소 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/walking-address")
public class WalkingAddressController {
    private final WalkingAddressService walkingAddressService;
    private final MemberDataService memberDataService;

    @ApiOperation(value = "[관리자] 산책장소 등록")
    @PostMapping("/new")
    public CommonResult setWalkingAddress(@RequestBody @Valid WalkingAddressAdminRequest request) {
        walkingAddressService.setWalkingAddress(request);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "[관리자/일반유저] 산책장소 전체 리스트")
    @GetMapping("/data")
    public ListResult<WalkingAddressUserFavoritesResponse> getWalkingAddresses() {
        return ResponseService.getListResult(walkingAddressService.getWalkingAddresses(),true);
    }

    @ApiOperation(value = "[관리자] 산책장소 수정")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "walkingAddressId", value = "산책 장소 시퀀스", required = true)
    )
    @PutMapping("/data/{walkingAddressId}")
    public CommonResult putWalkingAddress(@PathVariable long walkingAddressId, @RequestBody @Valid WalkingAddressAdminRequest request) {
        walkingAddressService.putWalkingAddress(walkingAddressId, request);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "[일반유저] 나의 즐겨찾기 장소 등록")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "memberId", value = "회원 시퀀스", required = true)
    )
    @PutMapping("/favorites-new/{memberId}")
    public CommonResult putMyWalkingAddressFavorites(@PathVariable long memberId, @RequestBody @Valid WalkingAddressUserFavoritesRequest request) {
        Member member = memberDataService.getMember(memberId);
        walkingAddressService.putMyWalkingAddressFavorites(member, request);
        return ResponseService.getSuccessResult();
    }

//    @ApiOperation(value = "[일반유저] 나의 즐겨찾기 장소 리스트")
//    @ApiImplicitParams(
//            @ApiImplicitParam(name = "memberId", value = "회원 시퀀스", required = true)
//    )
//    @GetMapping("/favorites-data/{memberId}")
//    public ListResult<WalkingAddressResponse> getMyWalkingAddressFavorites(@PathVariable long memberId) {
//        Member member = memberDataService.getMember(memberId);
//        return ResponseService.getListResult(walkingAddressService.getMyWalkingAddressFavorites(member),true);
//    }
}
