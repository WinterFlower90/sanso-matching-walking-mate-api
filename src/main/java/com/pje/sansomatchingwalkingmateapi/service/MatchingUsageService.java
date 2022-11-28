package com.pje.sansomatchingwalkingmateapi.service;

import com.pje.sansomatchingwalkingmateapi.entity.MatchingUsage;
import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.entity.WalkingAddress;
import com.pje.sansomatchingwalkingmateapi.exception.CApplyMemberOverlapException;
import com.pje.sansomatchingwalkingmateapi.exception.CMissingDataException;
import com.pje.sansomatchingwalkingmateapi.model.common.ListResult;
import com.pje.sansomatchingwalkingmateapi.model.matchingUsage.MyMatchingAcceptRequest;
import com.pje.sansomatchingwalkingmateapi.model.matchingUsage.MyMatchingListResponse;
import com.pje.sansomatchingwalkingmateapi.repository.MatchingUsageRepository;
import com.pje.sansomatchingwalkingmateapi.service.common.ListConvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static com.pje.sansomatchingwalkingmateapi.enums.MatchingStatus.WAIT;

@Service
@RequiredArgsConstructor
public class MatchingUsageService {
    private final MatchingUsageRepository matchingUsageRepository;


    /** [일반유저] 매칭 신청
     *
     * @param applyMember 신청한 회원
     * @param receiveMember 신청받은 회원
     * @param walkingAddress 신청한 장소
     */
    public void setMatching(Member applyMember, Member receiveMember, WalkingAddress walkingAddress) {
        if (applyMember.getId().equals(receiveMember.getId())) throw new CApplyMemberOverlapException();
        MatchingUsage matchingUsage = new MatchingUsage.MatchingUsageCreateBuilder(applyMember, receiveMember, walkingAddress).build();
        matchingUsageRepository.save(matchingUsage);
    }

    /**
     * [일반유저] 나의 신청 목록
     * @param member 나의 정보
     * @return 나의 신청 목록
     */
    public ListResult<MyMatchingListResponse> getMyMatchingApplyList(Member member) {
        List<MatchingUsage> matchingUsages = matchingUsageRepository.findAllByApplyMemberId_IdEqualsAndMatchingStatusOrderByDateUpdateDesc(member.getId(), WAIT);

        List<MyMatchingListResponse> result = new LinkedList<>();

        matchingUsages.forEach(matchingUsage -> {
            MyMatchingListResponse addItem = new MyMatchingListResponse.MyMatchingApplyResponseBuilder(matchingUsage).build();
            result.add(addItem);
        });

        return ListConvertService.settingResult(result);
    }

    /**
     * [일반유저] 내가 받은 매칭내역
     * @param member 나의 정보
     * @return 내가 받은 매칭내역
     */
    public ListResult<MyMatchingListResponse> getMyMatchingReceiveList(Member member) {
        List<MatchingUsage> matchingUsages = matchingUsageRepository.findAllByReceiveMemberId_IdEqualsAndMatchingStatusOrderByDateUpdateDesc(member.getId(), WAIT);

        List<MyMatchingListResponse> result = new LinkedList<>();

        matchingUsages.forEach(matchingUsage -> {
            MyMatchingListResponse addItem = new MyMatchingListResponse.MyMatchingApplyResponseBuilder(matchingUsage).build();
            result.add(addItem);
        });

        return ListConvertService.settingResult(result);
    }

    /**
     * [일반유저] 나의 매칭 수락
     * @param request 매칭 수락시 필요 정보
     */
    public void putMyMatchingAccept(MyMatchingAcceptRequest request) {
        MatchingUsage matchingUsage = matchingUsageRepository.findById(request.getMatchingUsageAcceptId()).orElseThrow(CMissingDataException::new);
        matchingUsage.putAccept(request);
        matchingUsageRepository.save(matchingUsage);
    }


//    /** [일반유저] 내가 매칭 신청한 내역 리스트 가져오기
//     *
//     * @param pageNum 현재 페이지
//     * @param member 회원 정보
//     * @return 페이지 정보 및 나의 매칭 신청내역 리스트 반환
//     */
//    public ListResult<MatchingListItem> getMyApplyMatchingList(int pageNum, Member member) {
//        PageRequest pageRequest = ListConvertService.getPageable(pageNum, 10);
//
//        Page<MatchingUsage> matchingUsages = matchingUsageRepository.findAllByApplyMemberIdOrderByDateBaseDesc(member.getId(), pageRequest);
//
//        List<MatchingListItem> result = new LinkedList<>();
//
//        matchingUsages.getContent().forEach(matchingUsage -> {
//            MatchingListItem addItem = new MatchingListItem.MatchingListItemBuilder(matchingUsage).build();
//            result.add(addItem);
//        });
//
//        return ListConvertService.settingResult(result, matchingUsages.getTotalElements(), matchingUsages.getTotalPages(), matchingUsages.getPageable().getPageNumber());
//    }
//
//    /** [일반유저] 내가 받은 매칭 신청내역 리스트 가져오기
//     *
//     * @param pageNum 현재 페이지
//     * @param member 회원 정보
//     * @return 페이지 정보 및 내가 받은 매칭 신청내역 리스트 반환
//     */
//    public ListResult<MatchingListItem> getMyReceiveMatchingList(int pageNum, Member member) {
//        PageRequest pageRequest = ListConvertService.getPageable(pageNum, 10);
//
//        Page<MatchingUsage> matchingUsages = matchingUsageRepository.findAllByReceiveMemberIdOrderByDateBaseDesc(member.getId(), pageRequest);
//
//        List<MatchingListItem> result = new LinkedList<>();
//
//        matchingUsages.getContent().forEach(matchingUsage -> {
//            MatchingListItem addItem = new MatchingListItem.MatchingListItemBuilder(matchingUsage).build();
//            result.add(addItem);
//        });
//
//        return ListConvertService.settingResult(result, matchingUsages.getTotalElements(), matchingUsages.getTotalPages(), matchingUsages.getPageable().getPageNumber());
//    }

}
