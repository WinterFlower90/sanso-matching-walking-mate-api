package com.pje.sansomatchingwalkingmateapi.service;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.enums.Gender;
import com.pje.sansomatchingwalkingmateapi.enums.MemberGroup;
import com.pje.sansomatchingwalkingmateapi.enums.Penalty;
import com.pje.sansomatchingwalkingmateapi.exception.*;
import com.pje.sansomatchingwalkingmateapi.lib.CommonCheck;
import com.pje.sansomatchingwalkingmateapi.model.common.ListResult;
import com.pje.sansomatchingwalkingmateapi.model.member.MemberCreateRequest;
import com.pje.sansomatchingwalkingmateapi.model.member.MemberInformationResponse;
import com.pje.sansomatchingwalkingmateapi.model.member.NickNameChangeRequest;
import com.pje.sansomatchingwalkingmateapi.model.member.NickNameResponse;
import com.pje.sansomatchingwalkingmateapi.repository.MemberRepository;
import com.pje.sansomatchingwalkingmateapi.service.common.ListConvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberDataService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    /**
     * [관리자] 서버 기동시 슈퍼계정 자동생성
     */
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

            setMember(MemberGroup.ROLE_ADMIN, createRequest);
        }
    }


    /**
     * [일반유저/관리자] 회원가입 메서드
     * @param memberGroup 권한 설정
     * @param createRequest 회원가입 정보
     */
    public void setMember(MemberGroup memberGroup, MemberCreateRequest createRequest) {
        if (!CommonCheck.checkUsername(createRequest.getUsername())) throw new CWrongUsernameType(); // 유효한 아이디 형식이 아닙니다 던지기
        if (!createRequest.getPassword().equals(createRequest.getPasswordRe())) throw new CWrongPasswordMatch(); // 비밀번호가 일치하지 않습니다 던지기
        if (!isNewUsername(createRequest.getUsername())) throw new CAlreadyDuplicateId(); // 중복된 아이디가 존재합니다 던지기

        createRequest.setPassword(passwordEncoder.encode(createRequest.getPassword()));

        Member member = new Member.MemberBuilder(memberGroup, createRequest).build();
        memberRepository.save(member);
    }

    /**
     * 회원가입시 동일 아이디 검증
     * @param username 아이디
     * @return ture/false
     */
    private boolean isNewUsername(String username) {
        long dupCount = memberRepository.countByUsername(username);
        return dupCount <= 0;
    }

    /**
     * 회원가입시 동일 휴대폰번호 검증
     * @param phone 휴대폰번호
     * @return ture/false
     */
    private boolean isNewPhone(String phone) {
        long dupCount = memberRepository.countByPhone(phone);
        return dupCount <= 0;
    }

    /**
     * 회원가입시 동일 닉네임 검증
     * @param nickName 닉네임
     * @return ture/false
     */
    private boolean isNewNickName(String nickName) {
        long dupCount = memberRepository.countByNickName(nickName);
        return dupCount <= 0;
    }

    //todo 사진 이미지 get + put 배우면 작성하기

    /**
     * [일반유저] 마이페이지 닉네임 가져오는 메서드
     * @param member 회원 정보
     * @return 회원 닉네임
     */
    public NickNameResponse getNickName(Member member) {
        return new NickNameResponse.NickNameResponseBuilder(member).build();
    }

    /**
     * [일반유저] 변경할 닉네임을 검증한 후 저장 or 예외처리
     * @param member 회원 정보
     * @param request 변경할 닉네임
     */
    public void putNickName(Member member, NickNameChangeRequest request) {
        // 회원 테이블에서 요청한 닉네임을 찾고
        Optional<Member> nickNameVerify = memberRepository.findAllByNickName(request.getNickName());

        // 닉네임이 사용중이라면 예외처리 "닉네임이 사용중 입니다."
        if (nickNameVerify.isPresent()) throw new CNickNameUsingException();

        // 기존 닉네임과 동일한 닉네임 일 경우 예외처리 "동일한 닉네임으로는 변경이 불가합니다."
        if (member.getNickName().equals(request.getNickName())) throw new CNickNameOverlapException();

        // 아닐경우 가져온 회원 정보에 새로운 닉네임 수정
        member.putNickName(request);

        // 아닐경우 닉네임을 수정 하고 저장
        member.putNickName(request);
        memberRepository.save(member);
    }

    /**
     * [관리자] 전체 회원정보 조회 (페이징처리)
     * @param pageNum 현재 페이지
     * @return 페이지정보 및 전체 회원정보
     */
    public ListResult<MemberInformationResponse> getMembers(int pageNum) {
        PageRequest pageRequest = ListConvertService.getPageable(pageNum, 3);

        Page<Member> members = memberRepository.findAllByIsEnabledOrderByIdAsc(true, pageRequest);

        List<MemberInformationResponse> result = new LinkedList<>();

        members.getContent().forEach(member -> {
            MemberInformationResponse addItem = new MemberInformationResponse.MemberInformationResponseBuilder(member).build();
            result.add(addItem);
        });

        return ListConvertService.settingResult(result, members.getTotalElements(), members.getTotalPages(), members.getPageable().getPageNumber());
    }

    /**
     * [관리자] 블랙 회원정보 조회 (페이징처리)
     * @param pageNum 현재 페이지
     * @return 페이지정보 및 블랙 회원정보
     */
    public ListResult<MemberInformationResponse> getBlackMembers(int pageNum) {
        PageRequest pageRequest = ListConvertService.getPageable(pageNum, 3);

        Page<Member> members = memberRepository.findAllByPenaltyOrderByIdAsc(Penalty.BLACK_LIST, pageRequest);

        List<MemberInformationResponse> result = new LinkedList<>();

        members.getContent().forEach(member -> {
            MemberInformationResponse addItem = new MemberInformationResponse.MemberInformationResponseBuilder(member).build();
            result.add(addItem);
        });

        return ListConvertService.settingResult(result, members.getTotalElements(), members.getTotalPages(), members.getPageable().getPageNumber());
    }

    /**
     * [관리자] 별점순 회원정보 조회 (페이징처리)
     * @param pageNum 현재 페이지
     * @return 페이지정보 및 별점순 회원정보
     */
    public ListResult<MemberInformationResponse> getStarPointMembers(int pageNum) {
        PageRequest pageRequest = ListConvertService.getPageable(pageNum, 10);

        Page<Member> members = memberRepository.findAllByOrderByAvgStarRatingDesc(pageRequest);

        List<MemberInformationResponse> result = new LinkedList<>();

        members.getContent().forEach(member -> {
            MemberInformationResponse addItem = new MemberInformationResponse.MemberInformationResponseBuilder(member).build();
            result.add(addItem);
        });

        return ListConvertService.settingResult(result, members.getTotalElements(), members.getTotalPages(), members.getPageable().getPageNumber());
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
