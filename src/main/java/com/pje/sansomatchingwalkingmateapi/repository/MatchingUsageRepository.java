package com.pje.sansomatchingwalkingmateapi.repository;

import com.pje.sansomatchingwalkingmateapi.entity.MatchingUsage;
import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.enums.MatchingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchingUsageRepository extends JpaRepository<MatchingUsage, Long> {
    Page<MatchingUsage> findAllByApplyMemberIdOrderByDateBaseDesc(Long memberId, Pageable pageable);
    Page<MatchingUsage> findAllByReceiveMemberIdOrderByDateBaseDesc(Long memberId, Pageable pageable);
    List<MatchingUsage> findAllByApplyMemberId_IdEqualsOrderByDateUpdateDesc(long applyMemberId);
    // 내가 신청한 매칭내역 조회
    List<MatchingUsage> findAllByApplyMemberId_IdEqualsAndMatchingStatusOrderByDateUpdateDesc(long applyMemberId, MatchingStatus matchingStatus);
    // 내가 받은 매칭내역 조회
    List<MatchingUsage> findAllByReceiveMemberId_IdEqualsAndMatchingStatusOrderByDateUpdateDesc(long applyMemberId, MatchingStatus matchingStatus);

}
