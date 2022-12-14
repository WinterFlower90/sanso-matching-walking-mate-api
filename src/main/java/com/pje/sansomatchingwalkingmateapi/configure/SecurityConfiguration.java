package com.pje.sansomatchingwalkingmateapi.configure;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers(AUTH_WHITELIST);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

        /*
    flutter 에서 header에 token 넣는법
    String? token = await TokenLib.getToken();

    Dio dio = Dio();
    dio.options.headers['Authorization'] = 'Bearer ' + token!; <-- 한줄 추가

    --- 아래 동일
     */

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                        .antMatchers(HttpMethod.GET, "/exception/**").permitAll() // 전체 허용
                //실질적으로 고쳐서 사용하는 부분
                        .antMatchers("/v1/login/**").permitAll() // 로그인 기능
                        .antMatchers("/v1/auth-test/test-admin").hasAnyRole("ADMIN") // /v1/test/로 시작하는 모든 주소는 ROLE_ADMIN, ROLE_USER 권한을 가진 유저만 접근 가능함.
                        .antMatchers("/v1/auth-test/test-user").hasAnyRole("USER") // /v1/test/로 시작하는 모든 주소는 ROLE_ADMIN, ROLE_USER 권한을 가진 유저만 접근 가능함.
                        .antMatchers("/v1/auth-test/test-all").hasAnyRole("ADMIN", "USER") // /v1/test/로 시작하는 모든 주소는 ROLE_ADMIN, ROLE_USER 권한을 가진 유저만 접근 가능함.
                        .antMatchers("/v1/auth-test/login-all/**").hasAnyRole("ADMIN", "USER")
                        .antMatchers("/v1/member/join").permitAll() // [일반유저/관리자] 회원등록
                //공지사항
                        .antMatchers("/v1/notice/new").hasAnyRole("ADMIN") // [관리자] 공지사항 등록(C)
                        .antMatchers("/v1/notice/list/all").hasAnyRole("ADMIN") // [관리자] 전체 공지사항 조회(R)
                        .antMatchers("/v1/notice/list/page/**").hasAnyRole("ADMIN", "USER") // [관리자/일반유저] 유효 공지사항 조회(R)
                        .antMatchers("/v1/notice/put/**").hasAnyRole("ADMIN") // [관리자] 공지사항 수정(U)
                        .antMatchers("/v1/notice/enable/**").hasAnyRole("ADMIN") // [관리자] 공지사항 게시여부 수정(U)
                //키워드
                        .antMatchers("/v1/keyword/new").hasAnyRole("ADMIN", "USER") // [관리자/일반유저] 키워드 등록(C)
                        .antMatchers("/v1/keyword/list/walking").hasAnyRole("ADMIN", "USER") // [관리자/일반유저] 키워드-산책관 리스트 조회(R)
                        .antMatchers("/v1/keyword/list/friend-i-want").hasAnyRole("ADMIN", "USER") // [관리자/일반유저] 키워드-나는 이런친구가 좋아요 리스트 조회(R)
                        .antMatchers("/v1/keyword/list/friend-you-want").hasAnyRole("ADMIN", "USER") // [관리자/일반유저] 키워드-나는 이런친구가 될게요 리스트 조회(R)
                        .antMatchers("/v1/keyword/put/walking").hasAnyRole("ADMIN", "USER") // [관리자/일반유저] 키워드-산책관 수정(U)
                        .antMatchers("/v1/keyword/put/friend-i-want").hasAnyRole("ADMIN", "USER") // [관리자/일반유저] 키워드-나는 이런친구가 좋아요 수정(U)
                        .antMatchers("/v1/keyword/put/friend-you-want").hasAnyRole("ADMIN", "USER") // [관리자/일반유저] 키워드-나는 이런친구가 될게요 수정(U)
                // 산책장소 API
                        .antMatchers("/v1/walking-address/new").hasAnyRole("ADMIN") // [관리자] 산책 장소 등록(C)
                        .antMatchers("/v1/walking-address/data").hasAnyRole("ADMIN", "USER") // [관리자/일반유저] 산책 리스트(R)
                        .antMatchers("/v1/walking-address/data/**").hasAnyRole("ADMIN") // [관리자] 산책 장소 수정(U)
                        .antMatchers("/v1/walking-address/favorites-new/**").hasAnyRole("USER") // [일반유저] 나의 즐겨찾기 장소 등록(C)
                        .antMatchers("/v1/walking-address/favorites-data").hasAnyRole("USER") // [일반유저] 나의 즐겨찾기 장소 리스트 조회(R)
                        .antMatchers("/v1/walking-address/favorites-data/**").hasAnyRole("USER") // [일반유저] 나의 즐겨찾기 장소의 회원 조회(R)
                // 회원
                        .antMatchers("/v1/member/join").permitAll() // [관리자/일반유저] 회원 등록(C)
                        .antMatchers("/v1/member/nickname").hasAnyRole("USER") // [일반유저] 닉네임 조회(R)
                        .antMatchers("/v1/member/nickname-change").hasAnyRole("USER") // [일반유저] 닉네임 수정(U)
                        .antMatchers("/v1/member/information/**").hasAnyRole("ADMIN") // [관리자] 회원정보 리스트 조회(R)
                        .antMatchers("/v1/member/my-profile").hasAnyRole("ADMIN", "USER") //[관리자/일반유저] 나의 프로필정보 조회(R)
                // 펫
                        .antMatchers("/v1/pet/new").hasAnyRole("ADMIN", "USER") // [관리자/일반유저] 펫 등록(C)
                        .antMatchers("/v1/pet/all").hasAnyRole("ADMIN", "USER") // [관리자/일반유저] 펫 목록 조회(R)
                        .antMatchers("/v1/pet/one/pet-id/**").hasAnyRole("ADMIN", "USER") // [관리자/일반유저] 각 펫의 정보 (한마리씩) 조회(R)
                        .antMatchers("/v1/pet/put-pet-name/pet-id/**").hasAnyRole("ADMIN", "USER") //[관리자/일반유저] 각 펫의 이름 수정(U)
                        .antMatchers("/v1/pet/put-pet-info/pet-id/**").hasAnyRole("ADMIN", "USER") //[관리자/일반유저] 각 펫의 정보 수정(U)
                // 매칭내역
                        .antMatchers("/v1/matching-usage/new").hasAnyRole("USER") // [일반유저] 매칭 신청(C)
                        .antMatchers("/v1/matching-usage/my-apply").hasAnyRole("USER") // [일반유저] 내가 신청한 매칭내역(R)
                        .antMatchers("/v1/matching-usage/my-receive").hasAnyRole("USER") // [일반유저] 내가 받은 매칭내역(R)
                        .antMatchers("/v1/matching-usage/my-accept").hasAnyRole("USER") // [일반유저] 나의 매칭 수락(U)
                        .anyRequest().hasRole("ADMIN") // 기본 접근 권한은 ROLE_ADMIN
                //고쳐서 사용하는 부분 끝
                .and()
                    .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                    .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                    .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}