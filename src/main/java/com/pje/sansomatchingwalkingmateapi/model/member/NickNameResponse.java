package com.pje.sansomatchingwalkingmateapi.model.member;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * [일반유저] 닉네임 출력 모델
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NickNameResponse {
    @ApiModelProperty(notes = "닉네임")
    private String nickName;

    private NickNameResponse(NickNameResponseBuilder builder) {
        this.nickName = builder.nickName;
    }

    public static class NickNameResponseBuilder implements CommonModelBuilder<NickNameResponse> {
        private final String nickName;

        public NickNameResponseBuilder(Member member) {
            this.nickName = member.getNickName() == null ? "-" : member.getNickName(); ;
        }

        @Override
        public NickNameResponse build() {
            return new NickNameResponse(this);
        }
    }
}

