package com.pje.sansomatchingwalkingmateapi.controller;


import com.pje.sansomatchingwalkingmateapi.enums.DropBoxDir;
import com.pje.sansomatchingwalkingmateapi.model.common.CommonResult;
import com.pje.sansomatchingwalkingmateapi.model.common.DropBoxItem;
import com.pje.sansomatchingwalkingmateapi.model.common.SingleResult;
import com.pje.sansomatchingwalkingmateapi.service.common.DropBoxService;
import com.pje.sansomatchingwalkingmateapi.service.common.ResponseService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "파일 관리")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/file")
public class FileController {
    private final DropBoxService dropBoxService;

    @ApiOperation(value = "공지사항 파일 업로드", authorizations = { @Authorization(value="jwtToken") })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "noticeId", value = "고객시퀀스", required = true),
    })
    @PostMapping("/notice/{noticeId}")
    public SingleResult<DropBoxItem> uploadCustomerFile(@PathVariable long noticeId, @RequestParam("file") MultipartFile file) throws Exception {
        return ResponseService.getSingleResult(dropBoxService.uploadFile(DropBoxDir.NOTICE, noticeId, file));
    }


    @ApiOperation(value = "파일 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "realPath", value = "파일 path", required = true),
    })
    @DeleteMapping("/remove")
    public CommonResult delCustomerFile(@RequestParam("realPath") String realPath) {
        dropBoxService.delFile(realPath);
        return ResponseService.getSuccessResult();
    }
}