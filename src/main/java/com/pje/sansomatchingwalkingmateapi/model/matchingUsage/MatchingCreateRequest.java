package com.pje.sansomatchingwalkingmateapi.model.matchingUsage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MatchingCreateRequest {

    @ApiModelProperty(notes = "매칭 신청 메세지(2-20글자)")
    @NotNull
    @Length(min = 2, max = 20)
    private String applyMessage;
}
