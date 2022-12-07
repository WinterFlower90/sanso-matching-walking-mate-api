package com.pje.sansomatchingwalkingmateapi.model.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DropBoxItem {
    @ApiModelProperty(notes = "드롭박스 경로")
    private String realPath;

    @ApiModelProperty(notes = "드롭박스 파일 아이디")
    private String fileId;

    @ApiModelProperty(notes = "파일명")
    private String fileName;

    @ApiModelProperty(notes = "공유링크")
    private String fileSharedLink;
}