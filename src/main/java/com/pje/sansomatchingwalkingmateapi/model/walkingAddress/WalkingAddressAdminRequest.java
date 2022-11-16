package com.pje.sansomatchingwalkingmateapi.model.walkingAddress;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WalkingAddressAdminRequest {
    @ApiModelProperty(value = "산책장소", required = true)
    @NotNull
    @Length(min = 2, max =20)
    private String walkingAddressName;

    @ApiModelProperty(value = "위도", required = true)
    @NotNull
    private Double latitude;

    @ApiModelProperty(value = "경도 ", required = true)
    @NotNull
    private Double longitude;
}
