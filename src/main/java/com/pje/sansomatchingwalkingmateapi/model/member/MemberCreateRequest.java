package com.pje.sansomatchingwalkingmateapi.model.member;

import com.pje.sansomatchingwalkingmateapi.enums.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class MemberCreateRequest {
    @ApiModelProperty(value = "아이디 (5~30자)", required = true)
    @NotNull
    @Length(min = 5, max = 30)
    private String username;

    @ApiModelProperty(value = "비밀번호 (8~20자)", required = true)
    @NotNull
    @Length(min = 8, max = 20)
    private String password;

    @ApiModelProperty(value = "비밀번호 확인 (8~20자)", required = true)
    @NotNull
    @Length(min = 8, max = 20)
    private String passwordRe;

    @ApiModelProperty(notes = "프로필 이미지")
    private String memberProfileImage;

    @ApiModelProperty(value = "별명 (2~20자)")
    @Length(min = 2, max = 20)
    private String nickName;

    @ApiModelProperty(value = "성별", required = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ApiModelProperty(value = "생년월일", required = true)
    @NotNull
    private LocalDate birthDay;

    @ApiModelProperty(value = "성별 (12~20자)", required = true)
    @NotNull
    @Length(min = 12, max = 20)
    private String phone;
}


