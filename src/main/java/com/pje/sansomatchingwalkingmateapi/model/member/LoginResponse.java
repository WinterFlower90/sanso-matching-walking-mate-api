package com.pje.sansomatchingwalkingmateapi.model.member;

import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponse {
    @ApiModelProperty(notes = "토큰")
    private String token;

    @ApiModelProperty(notes = "이름")
    private String name;

    private LoginResponse(LoginResponseBuilder builder) {
        this.token = builder.token;
        this.name = builder.name;
    }

    public static class LoginResponseBuilder implements CommonModelBuilder<LoginResponse> {
        private final String token;
        private final String name;

        public LoginResponseBuilder(String token, String name) {
            this.token = token;
            this.name = name;
        }

        @Override
        public LoginResponse build() {
            return new LoginResponse(this);
        }
    }
}
