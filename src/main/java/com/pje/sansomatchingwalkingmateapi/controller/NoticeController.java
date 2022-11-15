package com.pje.sansomatchingwalkingmateapi.controller;

import com.pje.sansomatchingwalkingmateapi.model.common.CommonResult;
import com.pje.sansomatchingwalkingmateapi.model.common.ListResult;
import com.pje.sansomatchingwalkingmateapi.model.notice.NoticeCreateRequest;
import com.pje.sansomatchingwalkingmateapi.model.notice.NoticeListItem;
import com.pje.sansomatchingwalkingmateapi.model.notice.NoticeSearchRequest;
import com.pje.sansomatchingwalkingmateapi.service.NoticeService;
import com.pje.sansomatchingwalkingmateapi.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(tags = "공지사항")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/notice")
public class NoticeController {
    private final NoticeService noticeService;

    @ApiOperation(value = "공지사항 등록하기")
    @PostMapping("/new")
    public CommonResult setNotice(@RequestBody @Valid NoticeCreateRequest request) {
        noticeService.setNotice(request);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "공지사항 게시 해제하기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "noticeId", value = "공지사항 시퀀스", required = true)
    })
    @PutMapping("/enable/{noticeId}")
    public CommonResult putNoticeEnable(@PathVariable long noticeId) {
        noticeService.putNoticeEnable(noticeId);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "공지사항 수정하기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "noticeId", value = "공지사항 시퀀스", required = true)
    })
    @PutMapping("/put/{noticeId}")
    public CommonResult putNotice(@PathVariable long noticeId, @RequestBody @Valid NoticeCreateRequest request) {
        noticeService.putNotice(noticeId, request);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "공지사항 유효한 게시물 + 작성일자 기간별 리스트 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "페이지 숫자", required = true),
    })
    @PostMapping("/list/page/{pageNum}")
    public ListResult<NoticeListItem> getEnableNoticeList(@PathVariable int pageNum, @RequestBody @Valid NoticeSearchRequest searchRequest) {
        return ResponseService.getListResult(noticeService.getEnableNoticeList(pageNum, searchRequest), true);
    }

    @ApiOperation(value = "공지사항 전체 리스트 조회")
    @GetMapping("/list/all")
    public ListResult<NoticeListItem> getNoticeList() {
        return ResponseService.getListResult(noticeService.getNoticeList(), true);
    }

}
