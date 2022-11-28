package com.pje.sansomatchingwalkingmateapi.model.matchingUsage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MatchingCreateRequest {
    @ApiModelProperty(notes = "신청받은 회원 시퀀스")
    @NotNull
    private Long receiveMemberId;

    @ApiModelProperty(notes = "산책장소 시퀀스")
    @NotNull
    private Long walkingAddressId;

}
