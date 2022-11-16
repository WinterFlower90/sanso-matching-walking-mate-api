package com.pje.sansomatchingwalkingmateapi.model.keyword;

import com.pje.sansomatchingwalkingmateapi.enums.keyword.ValuesTypeFriend;
import com.pje.sansomatchingwalkingmateapi.enums.keyword.ValuesTypePet;
import com.pje.sansomatchingwalkingmateapi.enums.keyword.ValuesTypeWalking;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class KeywordCreateRequest {

    @ApiModelProperty(notes = "펫 성격(선택)")
    @Enumerated(value = EnumType.STRING)
    @Length(max = 20)
    private ValuesTypePet valuesTypePet1;

    @ApiModelProperty(notes = "펫 성격(선택)")
    @Enumerated(value = EnumType.STRING)
    @Length(max = 20)
    private ValuesTypePet valuesTypePet2;

    @ApiModelProperty(notes = "펫 성격(선택)")
    @Enumerated(value = EnumType.STRING)
    @Length(max = 20)
    private ValuesTypePet valuesTypePet3;

    @ApiModelProperty(notes = "펫 성격(선택)")
    @Enumerated(value = EnumType.STRING)
    @Length(max = 20)
    private ValuesTypePet valuesTypePet4;

    //

    @ApiModelProperty(notes = "산책관(필수)")
    @NotNull
    @Length(max = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeWalking valuesTypeWalking1;

    @ApiModelProperty(notes = "산책관(필수)")
    @NotNull
    @Length(max = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeWalking valuesTypeWalking2;

    @ApiModelProperty(notes = "산책관(필수)")
    @NotNull
    @Length(max = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeWalking valuesTypeWalking3;

    @ApiModelProperty(notes = "산책관(선택)")
    @Length(max = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeWalking valuesTypeWalking4;

    @ApiModelProperty(notes = "산책관(선택)")
    @Length(max = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeWalking valuesTypeWalking5;

    @ApiModelProperty(notes = "산책관(선택)")
    @Length(max = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeWalking valuesTypeWalking6;

    //

    @ApiModelProperty(notes = "원하는 친구상(필수)")
    @NotNull
    @Length(max = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendIWant1;

    @ApiModelProperty(notes = "원하는 친구상(필수)")
    @NotNull
    @Length(max = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendIWant2;

    @ApiModelProperty(notes = "원하는 친구상(필수)")
    @NotNull
    @Length(max = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendIWant3;

    @ApiModelProperty(notes = "원하는 친구상(선택)")
    @Length(max = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendIWant4;

    @ApiModelProperty(notes = "원하는 친구상(선택)")
    @Length(max = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendIWant5;

    @ApiModelProperty(notes = "원하는 친구상(선택)")
    @Length(max = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendIWant6;

    //

    @ApiModelProperty(notes = "내가 될 친구상(필수)")
    @NotNull
    @Length(max = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendYouWant1;

    @ApiModelProperty(notes = "내가 될 친구상(필수)")
    @NotNull
    @Length(max = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendYouWant2;

    @ApiModelProperty(notes = "내가 될 친구상(필수)")
    @NotNull
    @Length(max = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendYouWant3;

    @ApiModelProperty(notes = "내가 될 친구상(선택)")
    @Length(max = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendYouWant4;

    @ApiModelProperty(notes = "내가 될 친구상(선택)")
    @Length(max = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendYouWant5;

    @ApiModelProperty(notes = "내가 될 친구상(선택)")
    @Length(max = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendYouWant6;
}
