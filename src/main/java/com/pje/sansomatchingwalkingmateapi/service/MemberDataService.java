package com.pje.sansomatchingwalkingmateapi.service;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.enums.MemberGroup;
import com.pje.sansomatchingwalkingmateapi.exception.CWrongPhoneNumberException;
import com.pje.sansomatchingwalkingmateapi.lib.CommonCheck;
import com.pje.sansomatchingwalkingmateapi.model.member.MemberCreateRequest;
import com.pje.sansomatchingwalkingmateapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDataService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void setFirstMember() {
        String username = "superadmin";
        String password = "idjh195233";
        boolean isSuperAdmin = isNewUsername(username);

        if (isSuperAdmin) {
            MemberCreateRequest createRequest = new MemberCreateRequest();
            createRequest.setUsername(username);
            createRequest.setPassword(password);
            createRequest.setPasswordRe(password);
            createRequest.setName("최고관리자");

            setMember(MemberGroup.ROLE_ADMIN, createRequest);
        }
    }

    public void setMember(MemberGroup memberGroup, MemberCreateRequest createRequest) {
        if (!CommonCheck.checkUsername(createRequest.getUsername())) throw new CWrongPhoneNumberException(); // 유효한 아이디 형식이 아닙니다 던지기
        if (!createRequest.getPassword().equals(createRequest.getPasswordRe())) throw new CWrongPhoneNumberException(); // 비밀번호가 일치하지 않습니다 던지기
        if (!isNewUsername(createRequest.getUsername())) throw new CWrongPhoneNumberException(); // 중복된 아이디가 존재합니다 던지기

        createRequest.setPassword(passwordEncoder.encode(createRequest.getPassword()));

        Member member = new Member.MemberBuilder(memberGroup, createRequest).build();
        memberRepository.save(member);
    }

    private boolean isNewUsername(String username) {
        long dupCount = memberRepository.countByUsername(username);
        return dupCount <= 0;
    }
}
