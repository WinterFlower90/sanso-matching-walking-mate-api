package com.pje.sansomatchingwalkingmateapi.model.pet;

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
public class PetInfoUpdateRequest {

    @NotNull
    @Length(max = 20)
    @ApiModelProperty(notes = "펫 이름")
    private String petName;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @ApiModelProperty(notes = "펫 성격 1")
    private ValuesTypePet valuesTypePet1;

    @Enumerated(value = EnumType.STRING)
    @ApiModelProperty(notes = "펫 성격 2")
    private ValuesTypePet valuesTypePet2;

    @Enumerated(value = EnumType.STRING)
    @ApiModelProperty(notes = "펫 성격 3")
    private ValuesTypePet valuesTypePet3;

    @Enumerated(value = EnumType.STRING)
    @ApiModelProperty(notes = "펫 성격 4")
    private ValuesTypePet valuesTypePet4;
}

