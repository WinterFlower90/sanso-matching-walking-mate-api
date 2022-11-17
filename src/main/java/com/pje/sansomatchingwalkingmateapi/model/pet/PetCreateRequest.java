package com.pje.sansomatchingwalkingmateapi.model.pet;

import com.pje.sansomatchingwalkingmateapi.enums.KindOfPet;
import com.pje.sansomatchingwalkingmateapi.enums.keyword.ValuesTypePet;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PetCreateRequest {

    @ApiModelProperty(notes = "펫 프로필 사진")
    private String petProfileImg;

    @NotNull
    @Length(max = 20)
    @ApiModelProperty(notes = "펫 이름")
    private String petName;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @ApiModelProperty(notes = "펫의 종 분류")
    private KindOfPet petType;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @ApiModelProperty(notes = "펫 성격 1")
    private ValuesTypePet valuesTypePet1;

    @ApiModelProperty(notes = "펫 성격 2")
    @Enumerated(value = EnumType.STRING)
    private ValuesTypePet valuesTypePet2;

    @ApiModelProperty(notes = "펫 성격 3")
    @Enumerated(value = EnumType.STRING)
    private ValuesTypePet valuesTypePet3;

    @ApiModelProperty(notes = "펫 성격 4")
    @Enumerated(value = EnumType.STRING)
    private ValuesTypePet valuesTypePet4;

}


