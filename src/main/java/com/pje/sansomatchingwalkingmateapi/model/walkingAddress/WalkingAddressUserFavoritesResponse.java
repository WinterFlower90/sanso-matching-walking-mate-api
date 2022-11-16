package com.pje.sansomatchingwalkingmateapi.model.walkingAddress;


import com.pje.sansomatchingwalkingmateapi.entity.WalkingAddress;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 산책장소 정보 가져오는 모델
 * 내가 즐겨 찾는 산책로 선택시 보여주는 용도
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WalkingAddressUserFavoritesResponse {
    @ApiModelProperty(value = "산책장소 시퀀스")
    private Long id;
    @ApiModelProperty(value = "산책장소")
    private String walkingAddressName;
    public WalkingAddressUserFavoritesResponse(WalkingAddressFavoritesResponseBuilder builder) {
        this.id = builder.id;
        this.walkingAddressName = builder.walkingAddressName;
    }
    public static class WalkingAddressFavoritesResponseBuilder implements CommonModelBuilder<WalkingAddressUserFavoritesResponse> {
        private final Long id;
        private final String walkingAddressName;

        public WalkingAddressFavoritesResponseBuilder(WalkingAddress walkingAddress) {
            this.id = walkingAddress.getId();
            this.walkingAddressName = walkingAddress.getWalkingAddressName();
        }

        @Override
        public WalkingAddressUserFavoritesResponse build() {
            return new WalkingAddressUserFavoritesResponse(this);
        }
    }
}
