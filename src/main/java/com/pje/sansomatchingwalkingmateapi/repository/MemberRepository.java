package com.pje.sansomatchingwalkingmateapi.repository;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.enums.Penalty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);

    long countByUsername(String username);
    // 동일 휴대폰 확인용
    long countByPhone(String phone);
    // 동일 닉네임 확인용
    long countByNickName(String nickName);
    // 동일 닉네임 정보 확인용
    Optional<Member> findAllByNickName(String nickName);
    // 전체 회원(탈퇴 제외) 정보 조회
    Page<Member> findAllByIsEnabledOrderByIdAsc(boolean IsEnabled, Pageable pageable);
    // 블랙회원 정보 조회
    Page<Member> findAllByPenaltyOrderByIdAsc(Penalty penalty, Pageable pageable);
    // 별점순 정보 조회
    Page<Member> findAllByOrderByAvgStarRatingDesc(Pageable pageable);

}
