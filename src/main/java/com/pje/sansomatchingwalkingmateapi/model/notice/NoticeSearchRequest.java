package com.pje.sansomatchingwalkingmateapi.model.notice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class NoticeSearchRequest {

    @ApiModelProperty(notes = "조회 년도")
    @NotNull
    private Integer dateYear;

    @ApiModelProperty(notes = "조회 월")
    @NotNull
    private Integer dateMonth;


}
