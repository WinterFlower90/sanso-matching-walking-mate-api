package com.pje.sansomatchingwalkingmateapi.model.notice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeCreateRequest {
    @ApiModelProperty(notes = "공지사항 제목")
    @NotNull
    @Length(min = 5, max = 20)
    private String title;

    @ApiModelProperty(notes = "공지사항 내용")
    @NotNull
    @Length(min = 10)
    private String note;

    @ApiModelProperty(notes = "첨부파일")
    private String uploadFile;

    @ApiModelProperty(notes = "첨부 파일 이름")
    private String fileName;
}
