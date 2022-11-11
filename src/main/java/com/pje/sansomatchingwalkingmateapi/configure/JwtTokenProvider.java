package com.pje.sansomatchingwalkingmateapi.configure;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final UserDetailsService userDetailsService;

    @Value("${spring.jwt.secret}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /** provider 토큰 생성
     *
     * @param username 회원 아이디
     * @param role roleKey
     * @param type 웹/앱용 토큰
     * @return 토큰 생성
     */
    public String createToken(String username, String role, String type) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);
        Date now = new Date();

        // 토큰 유효시간
        // 1000 밀리세컨드 = 1초
        // 기본으로 10시간 유효하게 설정해 줌. 왜냐. 아침에 출근해서 로그인하고 점심먹고 퇴근하면 대충 10시간이니까.
        // 앱용 토큰 같은 경우 유효시간 1년으로 설정해 줌. 앱에서 아침마다 로그인하라고하면 짜증나니까.
        long tokenValidMillisecond = 1000L * 60 * 60 * 10;
        if (type.equals("APP")) tokenValidMillisecond = 1000L * 60 * 60 * 24 * 365;

        // 토큰 생성해서 리턴.
        // jwt 사이트 참고.
        // 유효시간도 넣어줌. 생성요청한 시간 ~ 현재 + 위에서 설정된 유효초 만큼.
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // 토큰을 분석하여 인증정보를 가져옴.
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰을 파싱하여 username을 가져옴.
    // 토큰 생성시 username은 subject에 넣은 것 꼭 확인.
    // jwt 사이트 보면서 코드 이해하기.
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // 리졸브(resolve)라는 단어도 많이 씀. 단어 뜻 검색.
    public String resolveToken(HttpServletRequest request) {
        // rest api - header 인증 방식에서 Bearer 를 언제 사용하는지 보기.
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }

    // 유효시간을 검사하여 유효시간이 지났으면 false를 줌.
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
