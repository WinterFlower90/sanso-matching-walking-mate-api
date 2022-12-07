package com.pje.sansomatchingwalkingmateapi.controller;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.enums.NoticeIsEnable;
import com.pje.sansomatchingwalkingmateapi.model.common.CommonResult;
import com.pje.sansomatchingwalkingmateapi.model.common.ListResult;
import com.pje.sansomatchingwalkingmateapi.model.common.SingleResult;
import com.pje.sansomatchingwalkingmateapi.model.notice.NoticeCreateRequest;
import com.pje.sansomatchingwalkingmateapi.model.notice.NoticeListHaveNoteItem;
import com.pje.sansomatchingwalkingmateapi.model.notice.NoticeListItem;
import com.pje.sansomatchingwalkingmateapi.model.notice.NoticeSearchRequest;
import com.pje.sansomatchingwalkingmateapi.service.MemberDataService;
import com.pje.sansomatchingwalkingmateapi.service.NoticeService;
import com.pje.sansomatchingwalkingmateapi.service.common.ProfileService;
import com.pje.sansomatchingwalkingmateapi.service.common.ResponseService;
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
    private final ProfileService profileService;

    @ApiOperation(value = "공지사항 등록하기")
    @PostMapping("/new")
    public CommonResult setNotice(@RequestBody @Valid NoticeCreateRequest request) {
        Member member = profileService.getMemberData();
        noticeService.setNotice(member, request);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "공지사항 공지 or 해제하기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "noticeId", value = "공지사항 시퀀스", required = true),
            @ApiImplicitParam(name = "noticeIsEnable", value = "공지 or 해제", required = true),

    })
    @PutMapping("/enable/{noticeId}")
    public CommonResult putNoticeEnable(@PathVariable long noticeId, @RequestParam NoticeIsEnable noticeIsEnable) {
        noticeService.putNoticeEnable(noticeId, noticeIsEnable);
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

    @ApiOperation(value = "공지사항 전체 리스트 조회")
    @GetMapping("/list/all")
    public ListResult<NoticeListItem> getNoticeList() {
        return ResponseService.getListResult(noticeService.getNoticeList(), true);
    }

    @ApiOperation(value = "공지사항 유효한 게시물 + 작성일자 기간별 리스트 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "페이지 숫자", required = true),
    })
    @PostMapping("/list/page/{pageNum}")
    public ListResult<NoticeListItem> getEnableNoticeList(@PathVariable int pageNum, @RequestBody @Valid NoticeSearchRequest searchRequest) {
        return ResponseService.getListResult(noticeService.getEnableNoticeList(pageNum, searchRequest), true);
    }

    @ApiOperation(value = "[관리자] 페이지 + 기간 + 제목 검색 + 공지유무")
    @ApiImplicitParams({
            @ApiImplicitParam(name ="pageNum", value = "페이지 번호", required = true),
            @ApiImplicitParam(name = "dateYear", value = "연도", required = true),
            @ApiImplicitParam(name = "dateMonth", value = "달", required = true),
            @ApiImplicitParam(name = "searchTitle", value = "타이틀 검색"),
            @ApiImplicitParam(name = "noticeIsEnable", value = "공지/해제"),
    })
    @GetMapping("/list/search/{pageNum}")
    public ListResult<NoticeListItem> getSearchNoticeList(
            @PathVariable int pageNum,
            @RequestParam int dateYear,
            @RequestParam int dateMonth,
            @RequestParam(required = false) String searchTitle,
            @RequestParam(required = false) NoticeIsEnable noticeIsEnable) {
        if (noticeIsEnable != null) {
            return ResponseService.getListResult(noticeService.getSearchNoticeList(pageNum, dateYear, dateMonth, searchTitle, noticeIsEnable), true);
        } else {
            return ResponseService.getListResult(noticeService.getSearchNoticeList(pageNum, dateYear, dateMonth, searchTitle), true);
        }
    }



    @ApiOperation(value = "공지사항 전체 리스트 조회(제목)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "noticeId", value = "공지사항 시퀀스", required = true)
    })
    @GetMapping("/detail/{noticeId}")
    public SingleResult<NoticeListHaveNoteItem> getNoticeOne(@PathVariable long noticeId) {
        return ResponseService.getSingleResult(noticeService.getNoticeOne(noticeId));
    }

    @ApiOperation(value = "공지사항 전체 리스트 조회(제목+내용)")
    @GetMapping("/list/note/all")
    public ListResult<NoticeListHaveNoteItem> getNoticeNoteList() {
        return ResponseService.getListResult(noticeService.getNoticeNoteList(), true);
    }
}
