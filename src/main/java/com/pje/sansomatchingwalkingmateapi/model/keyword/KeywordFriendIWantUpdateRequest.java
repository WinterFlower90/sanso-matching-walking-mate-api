package com.pje.sansomatchingwalkingmateapi.model.keyword;

import com.pje.sansomatchingwalkingmateapi.enums.keyword.ValuesTypeFriend;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class KeywordFriendIWantUpdateRequest {

    @ApiModelProperty(notes = "원하는 친구상(필수)")
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendIWant1;

    @ApiModelProperty(notes = "원하는 친구상(필수)")
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendIWant2;

    @ApiModelProperty(notes = "원하는 친구상(필수)")
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendIWant3;

    @ApiModelProperty(notes = "원하는 친구상(선택)")
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendIWant4;

    @ApiModelProperty(notes = "원하는 친구상(선택)")
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendIWant5;

    @ApiModelProperty(notes = "원하는 친구상(선택)")
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendIWant6;
}
