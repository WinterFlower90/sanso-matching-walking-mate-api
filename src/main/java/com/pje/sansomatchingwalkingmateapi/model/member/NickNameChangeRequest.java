package com.pje.sansomatchingwalkingmateapi.model.member;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * [일반유저] 닉네임 변경 모델
 */
@Getter
@Setter
public class NickNameChangeRequest {
    @ApiModelProperty(value = "별명 (2~20자)", required = true)
    @NotNull
    @Length(min = 2, max = 20)
    private String nickName;
}

