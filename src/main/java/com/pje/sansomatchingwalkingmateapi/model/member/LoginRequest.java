package com.pje.sansomatchingwalkingmateapi.model.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginRequest {
    @ApiModelProperty(notes = "아이디", required = true)
    @NotNull
    @Length(min = 5, max = 30)
    private String username;

    @ApiModelProperty(notes = "비밀번호", required = true)
    @NotNull
    @Length(min = 8, max = 20)
    private String password;
}
