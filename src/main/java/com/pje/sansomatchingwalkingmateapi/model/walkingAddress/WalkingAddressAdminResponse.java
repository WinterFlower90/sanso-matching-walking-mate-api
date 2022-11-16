package com.pje.sansomatchingwalkingmateapi.model.walkingAddress;


import com.pje.sansomatchingwalkingmateapi.entity.WalkingAddress;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 지도에 산책 장소를 보여주기 위한 용도
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WalkingAddressAdminResponse {
    @ApiModelProperty(value = "산책장소 시퀀스")
    private Long id;
    @ApiModelProperty(value = "산책장소")
    private String walkingAddressName;
    @ApiModelProperty(value = "위도")
    private String latitude;
    @ApiModelProperty(value = "경도")
    private String longitude;
    public WalkingAddressAdminResponse(WalkingAddressResponseBuilder builder) {
        this.id = builder.id;
        this.walkingAddressName = builder.walkingAddressName;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
    }
    public static class WalkingAddressResponseBuilder implements CommonModelBuilder<WalkingAddressAdminResponse> {
        private final Long id;
        private final String walkingAddressName;
        private final String latitude;
        private final String longitude;

        public WalkingAddressResponseBuilder(WalkingAddress walkingAddress) {
            this.id = walkingAddress.getId();
            this.walkingAddressName = walkingAddress.getWalkingAddressName();
            this.latitude = walkingAddress.getLatitude().toString();
            this.longitude = walkingAddress.getLongitude().toString();
        }

        @Override
        public WalkingAddressAdminResponse build() {
            return new WalkingAddressAdminResponse(this);
        }
    }
}

