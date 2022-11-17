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
public class KeywordFriendYouWantUpdateRequest {

    @ApiModelProperty(notes = "내가 될 친구상(필수)")
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendYouWant1;

    @ApiModelProperty(notes = "내가 될 친구상(필수)")
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendYouWant2;

    @ApiModelProperty(notes = "내가 될 친구상(필수)")
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendYouWant3;

    @ApiModelProperty(notes = "내가 될 친구상(선택)")
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendYouWant4;

    @ApiModelProperty(notes = "내가 될 친구상(선택)")
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendYouWant5;

    @ApiModelProperty(notes = "내가 될 친구상(선택)")
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendYouWant6;
}
