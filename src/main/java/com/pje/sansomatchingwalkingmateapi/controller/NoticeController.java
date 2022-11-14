package com.pje.sansomatchingwalkingmateapi.controller;

import com.pje.sansomatchingwalkingmateapi.model.common.CommonResult;
import com.pje.sansomatchingwalkingmateapi.model.notice.NoticeCreateRequest;
import com.pje.sansomatchingwalkingmateapi.service.NoticeService;
import com.pje.sansomatchingwalkingmateapi.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
