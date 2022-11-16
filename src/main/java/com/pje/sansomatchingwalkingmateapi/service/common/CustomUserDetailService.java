package com.pje.sansomatchingwalkingmateapi.service.common;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.exception.CUserNameSignException;
import com.pje.sansomatchingwalkingmateapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;


    /** 로그인 아이디로 사용자 체크하기
     *
     * @param username the username identifying the user whose data is required.
     *                 parameter 값의 username 과 repository 의 username 을 대조.
     * @return member - UserDetails = Member
     * @throws UsernameNotFoundException - "가입된 사용자가 아닙니다." 출력
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).orElseThrow(CUserNameSignException::new);
        return member;
    }
}
