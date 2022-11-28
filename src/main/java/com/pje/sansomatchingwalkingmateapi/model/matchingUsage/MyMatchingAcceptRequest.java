package com.pje.sansomatchingwalkingmateapi.model.matchingUsage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@Setter
public class MyMatchingAcceptRequest {
    @ApiModelProperty(value = "수락한 매칭내역 시퀀스")
    @NotNull
    private Long MatchingUsageAcceptId;

    @ApiModelProperty(value = "약속 시간")
    @NotNull
    private LocalTime datePromise;

    @ApiModelProperty(value = "남길 메세지")
    @Length(max = 50)
    private String acceptMessage;
}
