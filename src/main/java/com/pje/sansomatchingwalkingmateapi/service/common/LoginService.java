package com.pje.sansomatchingwalkingmateapi.service.common;

import com.pje.sansomatchingwalkingmateapi.configure.JwtTokenProvider;
import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.enums.MemberGroup;
import com.pje.sansomatchingwalkingmateapi.exception.CMissingDataException;
import com.pje.sansomatchingwalkingmateapi.model.member.LoginRequest;
import com.pje.sansomatchingwalkingmateapi.model.member.LoginResponse;
import com.pje.sansomatchingwalkingmateapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    /**  로그인 타입은 WEB or APP (WEB인경우 토큰 유효시간 10시간, APP은 1년)
     *
     * @param memberGroup 회원 그룹
     * @param loginRequest loginRequest 항목값
     * @param loginType
     * if - 회원탈퇴인 경우인데 DB에 남아있으므로 들키지 않기 위해 회원정보가 없습니다 출력.
     * if - 일반회원이 최고관리자용으로 로그인하려하는 등의 경우 회원정보가 없습니다 일단 출력.
     * if - 패스워드가 일치하는지. 단방향 암호화를 했기 때문에 request 에서 받은 비밀번호를 암호화하고, DB에 저장된 암호화된 비밀번호랑 일치하는지 확인. 같지 않으면 비밀번호가 일치하지 않습니다 출력.
     * jwtTokenProvider 에게 토큰을 만들어달라고 부탁하기. (회원 아이디, 권한, 웹/앱용 토큰 제공)
     * @return jwtTokenProvider 가 만든 토큰값 주기
     */
    public LoginResponse doLogin(MemberGroup memberGroup, LoginRequest loginRequest, String loginType) {
        Member member = memberRepository.findByUsername(loginRequest.getUsername()).orElseThrow(CMissingDataException::new);

        if (!member.getIsEnabled()) throw new CMissingDataException(); // 회원탈퇴인 경우인데 DB에 남아있으므로 이걸 안 들키려면 회원정보가 없습니다 이걸로 던져야 함.
        if (!member.getMemberGroup().equals(memberGroup)) throw new CMissingDataException(); // 일반회원이 최고관리자용으로 로그인하려거나 이런 경우이므로 메세지는 회원정보가 없습니다로 일단 던짐.
        if (!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) throw new CMissingDataException(); // 비밀번호가 일치하지 않습니다 던짐

        String token = jwtTokenProvider.createToken(String.valueOf(member.getUsername()), member.getMemberGroup().toString(), loginType);

        return new LoginResponse.LoginResponseBuilder(token).build();
    }
}
