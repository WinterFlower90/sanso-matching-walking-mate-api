package com.pje.sansomatchingwalkingmateapi.model.keyword;

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
public class KeywordWalkingUpdateRequest {

    @ApiModelProperty(notes = "산책관(필수)")
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeWalking valuesTypeWalking1;

    @ApiModelProperty(notes = "산책관(필수)")
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeWalking valuesTypeWalking2;

    @ApiModelProperty(notes = "산책관(필수)")
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeWalking valuesTypeWalking3;

    @ApiModelProperty(notes = "산책관(선택)")
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeWalking valuesTypeWalking4;

    @ApiModelProperty(notes = "산책관(선택)")
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeWalking valuesTypeWalking5;

    @ApiModelProperty(notes = "산책관(선택)")
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeWalking valuesTypeWalking6;

}
