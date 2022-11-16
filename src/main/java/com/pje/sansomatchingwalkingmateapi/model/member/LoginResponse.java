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

    private LoginResponse(LoginResponseBuilder builder) {
        this.token = builder.token;
    }

    public static class LoginResponseBuilder implements CommonModelBuilder<LoginResponse> {
        private final String token;

        public LoginResponseBuilder(String token) {
            this.token = token;
        }

        @Override
        public LoginResponse build() {
            return new LoginResponse(this);
        }
    }
}
