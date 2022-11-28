package com.pje.sansomatchingwalkingmateapi.model.matchingUsage;

import com.pje.sansomatchingwalkingmateapi.entity.MatchingUsage;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * [일반유저] 나의 매칭신청 목록 모델
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyMatchingListResponse {
    @ApiModelProperty(value = "매칭내역 시퀀스")
    private Long matchingUsageId;
    @ApiModelProperty(value = "회원 시퀀스")
    private Long memberId;
    @ApiModelProperty(value = "프로필 사진")
    private String memberProfileImage;
    @ApiModelProperty(value = "닉네임")
    private String nickName;
    @ApiModelProperty(value = "산책장소")
    private String walkingAddress;

    private MyMatchingListResponse(MyMatchingApplyResponseBuilder builder) {
        this.matchingUsageId = builder.matchingUsageId;
        this.memberId = builder.memberId;
        this.memberProfileImage = builder.memberProfileImage;
        this.nickName = builder.nickName;
        this.walkingAddress = builder.walkingAddress;
    }
    public static class MyMatchingApplyResponseBuilder implements CommonModelBuilder<MyMatchingListResponse> {
        private final Long matchingUsageId;
        private final Long memberId;
        private final String memberProfileImage;
        private final String nickName;
        private final String walkingAddress;


        public MyMatchingApplyResponseBuilder(MatchingUsage matchingUsage) {
            this.matchingUsageId = matchingUsage.getId();
            this.memberId = matchingUsage.getReceiveMemberId().getId();
            this.memberProfileImage = matchingUsage.getReceiveMemberId().getMemberProfileImage();
            this.nickName = matchingUsage.getReceiveMemberId().getNickName();
            this.walkingAddress = matchingUsage.getWalkingAddressId().getWalkingAddressName();
        }

        @Override
        public MyMatchingListResponse build() {
            return new MyMatchingListResponse(this);
        }
    }
}

