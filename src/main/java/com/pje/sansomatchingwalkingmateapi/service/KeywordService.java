package com.pje.sansomatchingwalkingmateapi.service;

import com.pje.sansomatchingwalkingmateapi.entity.Keyword;
import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.exception.CMissingDataException;
import com.pje.sansomatchingwalkingmateapi.model.common.ListResult;
import com.pje.sansomatchingwalkingmateapi.model.keyword.*;
import com.pje.sansomatchingwalkingmateapi.repository.KeywordRepository;
import com.pje.sansomatchingwalkingmateapi.repository.MemberRepository;
import com.pje.sansomatchingwalkingmateapi.service.common.ListConvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class KeywordService {
    private final KeywordRepository keywordRepository;

    /** 키워드 초기 등록
     * @param request 항목 값 입력받는다
     */
    public void setKeyword(Member member, KeywordCreateRequest request) {
        Keyword keyword = new Keyword.KeywordBuilder(member, request).build();
        keywordRepository.save(keyword);
    }

    /** 키워드-산책관 수정
     * @param member 회원을 받는다
     * @param updateRequest 항목 값 입력받는다
     */
    public void putKeywordWalking(Member member, KeywordWalkingUpdateRequest updateRequest) {
        Keyword keyword = keywordRepository.findById(member.getId()).orElseThrow(CMissingDataException::new);
        keyword.putKeywordWalking(updateRequest);
        keywordRepository.save(keyword);
    }

    /** 키워드-원하는 친구상 수정
     * @param member 회원을 받는다
     * @param updateRequest 항목 값 입력받는다
     */
    public void putKeywordFriendIWant(Member member, KeywordFriendIWantUpdateRequest updateRequest) {
        Keyword keyword = keywordRepository.findById(member.getId()).orElseThrow(CMissingDataException::new);
        keyword.putKeywordFriendIWant(updateRequest);
        keywordRepository.save(keyword);
    }

    /** 키워드-내가 될 친구상 수정
     * @param member 회원을 받는다
     * @param updateRequest 항목 값 입력받는다
     */
    public void putKeywordFriendYouWant(Member member, KeywordFriendYouWantUpdateRequest updateRequest) {
        Keyword keyword = keywordRepository.findById(member.getId()).orElseThrow(CMissingDataException::new);
        keyword.putKeywordFriendYouWant(updateRequest);
        keywordRepository.save(keyword);
    }

    /** 나의 '산책관' 키워드 리스트 가져오기
     *
     * @param member 회원을 받는다
     * @return 나의 KeywordWalkingListItem 반환
     */
    public ListResult<KeywordWalkingListItem> getMemberKeywordWalkingList(Member member) {
        List<Keyword> keywordList = keywordRepository.findAllByMember_Id(member.getId());
        List<KeywordWalkingListItem> result = new LinkedList<>();
        keywordList.forEach(keyword -> {
            KeywordWalkingListItem addItem = new KeywordWalkingListItem.KeywordWalkingListItemBuilder(keyword).build();
            result.add(addItem);
        });
        return ListConvertService.settingResult(result);
    }

    /** 나의 '나는 이런 친구가 좋아요' 키워드 리스트 가져오기
     *
     * @param member 회원을 받는다
     * @return 나의 KeywordFriendIWantListItem 반환
     */
    public ListResult<KeywordFriendIWantListItem> getMemberKeywordFriendIWantList(Member member) {
        List<Keyword> keywordList = keywordRepository.findAllByMember_Id(member.getId());
        List<KeywordFriendIWantListItem> result = new LinkedList<>();
        keywordList.forEach(keyword -> {
            KeywordFriendIWantListItem addItem = new KeywordFriendIWantListItem.KeywordFriendIWantListItemBuilder(keyword).build();
            result.add(addItem);
        });
        return ListConvertService.settingResult(result);
    }

    /** 나의 '나는 이런 친구가 될게요' 키워드 리스트 가져오기
     *
     * @param member 회원을 받는다
     * @return 나의 KeywordFriendYouWantListItem 반환
     */
    public ListResult<KeywordFriendYouWantListItem> getMemberKeywordFriendYouWantList(Member member) {
        List<Keyword> keywordList = keywordRepository.findAllByMember_Id(member.getId());
        List<KeywordFriendYouWantListItem> result = new LinkedList<>();
        keywordList.forEach(keyword -> {
            KeywordFriendYouWantListItem addItem = new KeywordFriendYouWantListItem.KeywordFriendYouWantListItemBuilder(keyword).build();
            result.add(addItem);
        });
        return ListConvertService.settingResult(result);
    }


}
