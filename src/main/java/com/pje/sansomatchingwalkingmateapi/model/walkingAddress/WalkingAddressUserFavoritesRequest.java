package com.pje.sansomatchingwalkingmateapi.model.walkingAddress;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalkingAddressUserFavoritesRequest {
    @ApiModelProperty(value = "즐겨 찾기 산책 장소1")
    private Long walkingAddressId1;

    @ApiModelProperty(value = "즐겨 찾기 산책 장소2")
    private Long walkingAddressId2;

    @ApiModelProperty(value = "즐겨 찾기 산책 장소3")
    private Long walkingAddressId3;
}

