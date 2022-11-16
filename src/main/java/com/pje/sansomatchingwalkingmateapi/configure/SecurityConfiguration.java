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
                // 산책장소 API
                        .antMatchers("/v1/walking-address/new").hasAnyRole("ADMIN") // [관리자] 산책 장소 등록(C)
                        .antMatchers("/v1/walking-address/data").hasAnyRole("ADMIN", "USER") // [관리자/일반유저] 산책 리스트(R)
                        .antMatchers("/v1/walking-address/data/**").hasAnyRole("ADMIN") // [관리자] 산책 장소 수정(U)
                        .antMatchers("/v1/walking-address/favorites-new/**").hasAnyRole("USER") // [일반유저] 나의 즐겨찾기 장소 등록(C)
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
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}