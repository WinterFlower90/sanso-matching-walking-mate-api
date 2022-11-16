package com.pje.sansomatchingwalkingmateapi.service.common;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.exception.CAccessDeniedException;
import com.pje.sansomatchingwalkingmateapi.exception.CMissingDataException;
import com.pje.sansomatchingwalkingmateapi.exception.CNoMemberDataException;
import com.pje.sansomatchingwalkingmateapi.model.member.ProfileResponse;
import com.pje.sansomatchingwalkingmateapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final MemberRepository memberRepository;

    /** 토큰을 멤버 엔티티로 변환해서 필요할때 가져다가 씀
     * 멤버 엔티티가 없을때는 권한체크함
     * 토큰에 있는 정보 -> 씨큐리티에서 체크
     * @return member entity
     */
    public Member getMemberData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Member member = memberRepository.findByUsername(username).orElseThrow(CNoMemberDataException::new); // 회원정보가 없습니다 던지기
        if (!member.getIsEnabled()) throw new CAccessDeniedException(); // 회원이 탈퇴상태라면 권한이 없습니다 던지기
        return member;
    }

    public ProfileResponse getProfile() {
        Member member = getMemberData();
        return new ProfileResponse.ProfileResponseBuilder(member).build();
    }
}
