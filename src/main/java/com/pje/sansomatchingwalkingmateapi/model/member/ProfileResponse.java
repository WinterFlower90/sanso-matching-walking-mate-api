package com.pje.sansomatchingwalkingmateapi.model.member;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import com.pje.sansomatchingwalkingmateapi.lib.CommonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileResponse {
    @ApiModelProperty(notes = "시퀀스")
    private Long id;

    @ApiModelProperty(notes = "회원그룹")
    private String memberGroup;

    @ApiModelProperty(notes = "회원그룹 한글명")
    private String memberGroupName;

    @ApiModelProperty(notes = "아이디")
    private String username;

    @ApiModelProperty(notes = "이름")
    private String name;

    @ApiModelProperty(notes = "가입일")
    private String dateCreate;

    private ProfileResponse(ProfileResponseBuilder builder) {
        this.id = builder.id;
        this.memberGroup = builder.memberGroup;
        this.memberGroupName = builder.memberGroupName;
        this.username = builder.username;
        this.name = builder.name;
        this.dateCreate = builder.dateCreate;
    }

    public static class ProfileResponseBuilder implements CommonModelBuilder<ProfileResponse> {
        private final Long id;
        private final String memberGroup;
        private final String memberGroupName;
        private final String username;
        private final String name;
        private final String dateCreate;

        public ProfileResponseBuilder(Member member) {
            this.id = member.getId();
            this.memberGroup = member.getMemberGroup().toString();
            this.memberGroupName = member.getMemberGroup().getName();
            this.username = member.getUsername();
            this.name = member.getName();
            this.dateCreate = CommonFormat.convertLocalDateTimeToString(member.getDateCreate());
        }

        @Override
        public ProfileResponse build() {
            return new ProfileResponse(this);
        }
    }
}
