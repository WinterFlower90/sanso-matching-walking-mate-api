package com.pje.sansomatchingwalkingmateapi.model.pet;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PetNameUpdateRequest {
    @NotNull
    @Length(max = 20)
    @ApiModelProperty(notes = "펫 이름")
    private String petName;

}
