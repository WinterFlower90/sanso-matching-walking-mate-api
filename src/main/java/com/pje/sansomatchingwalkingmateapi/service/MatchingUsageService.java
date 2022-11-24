package com.pje.sansomatchingwalkingmateapi.service;

import com.pje.sansomatchingwalkingmateapi.entity.MatchingUsage;
import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.model.matchingUsage.MatchingCreateRequest;
import com.pje.sansomatchingwalkingmateapi.repository.MatchingUsageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchingUsageService {
    private final MatchingUsageRepository matchingUsageRepository;

//    public void setMatchingApply(Member member, MatchingCreateRequest request) {
//    MatchingUsage matchingUsage = new MatchingUsage.MatchingUsageCreateBuilder(member, request).build();
//    matchingUsageRepository.save(matchingUsage);
//    }
    //내 기준 신청 create? 상대기준 create? 신청회원=로그인한사람? 신청받은회원 id? 신청받은회원이 나라면? ...
}
