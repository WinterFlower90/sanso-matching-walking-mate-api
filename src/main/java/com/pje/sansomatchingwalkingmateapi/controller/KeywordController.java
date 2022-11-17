package com.pje.sansomatchingwalkingmateapi.controller;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.model.common.CommonResult;
import com.pje.sansomatchingwalkingmateapi.model.common.ListResult;
import com.pje.sansomatchingwalkingmateapi.model.keyword.*;
import com.pje.sansomatchingwalkingmateapi.service.KeywordService;
import com.pje.sansomatchingwalkingmateapi.service.common.ProfileService;
import com.pje.sansomatchingwalkingmateapi.service.common.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "키워드")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/keyword")
public class KeywordController {
    private final KeywordService keywordService;
    private final ProfileService profileService;

    @ApiOperation(value = "나의 키워드 등록하기")
    @PostMapping("/new")
    public CommonResult setKeyword(@RequestBody @Valid KeywordCreateRequest request) {
        Member member = profileService.getMemberData();
        keywordService.setKeyword(member, request);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "나의 '나는 이런 친구가 될게요' 리스트 가져오기")
    @GetMapping("/list/friend-you-want")
    public ListResult<KeywordFriendYouWantListItem> getMemberKeywordFriendYouWantList() {
        Member member = profileService.getMemberData();
        return ResponseService.getListResult(keywordService.getMemberKeywordFriendYouWantList(member), true);
    }

    @ApiOperation(value = "나의 '나는 이런 친구가 좋아요' 리스트 가져오기")
    @GetMapping("/list/friend-i-want")
    public ListResult<KeywordFriendIWantListItem> getMemberKeywordFriendIWantList() {
        Member member = profileService.getMemberData();
        return ResponseService.getListResult(keywordService.getMemberKeywordFriendIWantList(member), true);
    }

    @ApiOperation(value = "나의 '산책관' 리스트 가져오기")
    @GetMapping("/list/walking")
    public ListResult<KeywordWalkingListItem> getMemberKeywordWalkingList() {
        Member member = profileService.getMemberData();
        return ResponseService.getListResult(keywordService.getMemberKeywordWalkingList(member), true);
    }

    @ApiOperation(value = "나의 '산책관' 수정하기")
    @PostMapping("/put/walking")
    public CommonResult putKeywordWalking(@RequestBody @Valid KeywordWalkingUpdateRequest updateRequest) {
        Member member = profileService.getMemberData();
        keywordService.putKeywordWalking(member, updateRequest);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "나의 '나는 이런 친구가 좋아요' 수정하기")
    @PostMapping("/put/friend-i-want")
    public CommonResult putKeywordFriendIWant(@RequestBody @Valid KeywordFriendIWantUpdateRequest updateRequest) {
        Member member = profileService.getMemberData();
        keywordService.putKeywordFriendIWant(member, updateRequest);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "나의 '나는 이런 친구가 될게요' 수정하기")
    @PostMapping("/put/friend-you-want")
    public CommonResult putKeywordFriendYouWant(@RequestBody @Valid KeywordFriendYouWantUpdateRequest updateRequest) {
        Member member = profileService.getMemberData();
        keywordService.putKeywordFriendYouWant(member, updateRequest);
        return ResponseService.getSuccessResult();
    }
}
