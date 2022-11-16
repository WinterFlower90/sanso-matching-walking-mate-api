package com.pje.sansomatchingwalkingmateapi.service;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.enums.Gender;
import com.pje.sansomatchingwalkingmateapi.enums.MemberGroup;
import com.pje.sansomatchingwalkingmateapi.exception.*;
import com.pje.sansomatchingwalkingmateapi.lib.CommonCheck;
import com.pje.sansomatchingwalkingmateapi.model.member.MemberCreateRequest;
import com.pje.sansomatchingwalkingmateapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
            createRequest.setNickName("최고관리자");
            createRequest.setGender(Gender.MAN);
            createRequest.setBirthDay(LocalDate.now());
            createRequest.setPhone("070-1234-1234");
            createRequest.setIsPet(false);


            setMember(MemberGroup.ROLE_ADMIN, createRequest);
        }
    }

    public void setMember(MemberGroup memberGroup, MemberCreateRequest createRequest) {
        if (!CommonCheck.checkUsername(createRequest.getUsername())) throw new CWrongUsernameType(); // 유효한 아이디 형식이 아닙니다 던지기
        if (!createRequest.getPassword().equals(createRequest.getPasswordRe())) throw new CWrongPasswordMatch(); // 비밀번호가 일치하지 않습니다 던지기
        if (!isNewUsername(createRequest.getUsername())) throw new CAlreadyDuplicateId(); // 중복된 아이디가 존재합니다 던지기

        createRequest.setPassword(passwordEncoder.encode(createRequest.getPassword()));

        Member member = new Member.MemberBuilder(memberGroup, createRequest).build();
        memberRepository.save(member);
    }

    private boolean isNewUsername(String username) {
        long dupCount = memberRepository.countByUsername(username);
        return dupCount <= 0;
    }

    /**
     * 회원정보 가져오기
     * @param memberId 회원 시퀀스
     * @return 해당 회원정보
     */
    public Member getMember(long memberId) {
        return memberRepository.findById(memberId).orElseThrow(CMissingDataException::new);
    }

}
