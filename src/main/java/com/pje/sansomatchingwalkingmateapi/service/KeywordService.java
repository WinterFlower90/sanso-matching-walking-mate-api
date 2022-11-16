package com.pje.sansomatchingwalkingmateapi.service;

import com.pje.sansomatchingwalkingmateapi.entity.Keyword;
import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.exception.CMissingDataException;
import com.pje.sansomatchingwalkingmateapi.model.keyword.KeywordCreateRequest;
import com.pje.sansomatchingwalkingmateapi.model.keyword.KeywordFriendIWantUpdateRequest;
import com.pje.sansomatchingwalkingmateapi.model.keyword.KeywordFriendYouWantUpdateRequest;
import com.pje.sansomatchingwalkingmateapi.model.keyword.KeywordWalkingUpdateRequest;
import com.pje.sansomatchingwalkingmateapi.repository.KeywordRepository;
import com.pje.sansomatchingwalkingmateapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KeywordService {
    private final KeywordRepository keywordRepository;
    private final MemberRepository memberRepository;

    /** 키워드 초기 등록
     * @param request 항목 값 입력받는다
     */
    public void setKeyword(Member member, KeywordCreateRequest request) {
        Keyword keyword = new Keyword.KeywordBuilder(member, request).build();
        keywordRepository.save(keyword);
    }

    /** 키워드-산책관 수정
     * @param memberId 회원 시퀀스를 받는다
     * @param member 회원을 받는다
     * @param updateRequest 항목 값 입력받는다
     */
    public void putKeywordWalking(long memberId, Member member, KeywordWalkingUpdateRequest updateRequest) {
        Keyword keyword = keywordRepository.findByMember_Id(memberId).orElseThrow(CMissingDataException::new);
        keyword.putKeywordWalking(member, updateRequest);
        keywordRepository.save(keyword);
    }

    /** 키워드-원하는 친구상 수정
     * @param keywordId 키워드 시퀀스를 받는다
     * @param member 회원을 받는다
     * @param updateRequest 항목 값 입력받는다
     */
    public void putKeywordFriendIWant(long keywordId, Member member, KeywordFriendIWantUpdateRequest updateRequest) {
        Keyword keyword = keywordRepository.findById(keywordId).orElseThrow(CMissingDataException::new);
        keyword.putKeywordFriendIWant(member, updateRequest);
        keywordRepository.save(keyword);
    }

    /** 키워드-내가 될 친구상 수정
     * @param keywordId 키워드 시퀀스를 받는다
     * @param member 회원을 받는다
     * @param updateRequest 항목 값 입력받는다
     */
    public void putKeywordFriendYouWant(long keywordId, Member member, KeywordFriendYouWantUpdateRequest updateRequest) {
        Keyword keyword = keywordRepository.findById(keywordId).orElseThrow(CMissingDataException::new);
        keyword.putKeywordFriendYouWant(member, updateRequest);
        keywordRepository.save(keyword);
    }
}
