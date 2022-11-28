package com.pje.sansomatchingwalkingmateapi.entity;


import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import com.pje.sansomatchingwalkingmateapi.model.walkingAddress.WalkingAddressAdminRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.pje.sansomatchingwalkingmateapi.lib.CommonDate.getNowTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WalkingAddress {
    @ApiModelProperty(value = "시퀀스")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 시퀀스

    @ApiModelProperty(value = "산책 장소")
    @Column(nullable = false, length = 20)
    private String walkingAddressName; // 산책 장소

    @ApiModelProperty(value = "위도")
    @Column(nullable = false)
    private Double latitude; // 위도

    @ApiModelProperty(value = "경도")
    @Column(nullable = false)
    private Double longitude; // 경도

    @ApiModelProperty(value = "등록시간")
    @Column(nullable = false)
    private LocalDateTime dateCreate; // 등록시간

    @ApiModelProperty(value = "수정시간")
    @Column(nullable = false)
    private LocalDateTime dateUpdate; // 수정시간

    public void putWalkingAddress(WalkingAddressAdminRequest request) {
        this.walkingAddressName = request.getWalkingAddressName();
        this.latitude = request.getLatitude();
        this.longitude = request.getLongitude();
        this.dateUpdate = getNowTime();
    }
    private WalkingAddress(WalkingAddressBuilder builder) {
        this.walkingAddressName = builder.walkingAddressName;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.dateCreate = builder.dateCreate;
        this.dateUpdate = builder.dateUpdate;
    }
    public static class WalkingAddressBuilder implements CommonModelBuilder<WalkingAddress> {
        private final String walkingAddressName; // 산책 장소
        private final Double latitude; // 위도
        private final Double longitude; // 경도
        private final LocalDateTime dateCreate; // 등록시간
        private final LocalDateTime dateUpdate; // 수정시간

        public WalkingAddressBuilder(WalkingAddressAdminRequest request) {
            this.walkingAddressName = request.getWalkingAddressName();
            this.latitude = request.getLatitude();
            this.longitude = request.getLongitude();
            this.dateCreate = getNowTime();
            this.dateUpdate = getNowTime();
        }
        @Override
        public WalkingAddress build() {
            return new WalkingAddress(this);
        }
    }
}
