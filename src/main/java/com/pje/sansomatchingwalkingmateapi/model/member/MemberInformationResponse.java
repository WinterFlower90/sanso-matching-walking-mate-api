package com.pje.sansomatchingwalkingmateapi.model.member;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * [관리자] 웹페이지에서 보여줄 회원정보 모델
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInformationResponse {
    @ApiModelProperty(value = "회원 시퀀스")
    private Long id;
    @ApiModelProperty(value = "아이디")
    private String username;
    @ApiModelProperty(value = "닉네임")
    private String nickName;
    @ApiModelProperty(value = "가입일")
    private String dateJoin;
    @ApiModelProperty(value = "평점")
    private String avgStarRating;
    @ApiModelProperty(value = "패널티")
    private String penalty;
    private MemberInformationResponse(MemberInformationResponseBuilder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.nickName = builder.nickName;
        this.dateJoin = builder.dateJoin;
        this.avgStarRating = builder.avgStarRating;
        this.penalty = builder.penalty;
    }
    public static class MemberInformationResponseBuilder implements CommonModelBuilder<MemberInformationResponse> {
        private final Long id;
        private final String username;
        private final String nickName;
        private final String dateJoin;
        private final String avgStarRating;
        private final String penalty;

        public MemberInformationResponseBuilder(Member member) {
            this.id = member.getId();
            this.username = member.getUsername();
            this.nickName = member.getNickName();
            this.dateJoin = member.getDateJoin().toString();
            this.avgStarRating = String.format("%.1f", member.getAvgStarRating());
            this.penalty = member.getPenalty().getName();
        }

        @Override
        public MemberInformationResponse build() {
            return new MemberInformationResponse(this);
        }
    }
}



