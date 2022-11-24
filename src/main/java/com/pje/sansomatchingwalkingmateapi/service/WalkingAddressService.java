package com.pje.sansomatchingwalkingmateapi.service;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.entity.WalkingAddress;
import com.pje.sansomatchingwalkingmateapi.exception.CMissingDataException;
import com.pje.sansomatchingwalkingmateapi.model.common.ListResult;
import com.pje.sansomatchingwalkingmateapi.model.walkingAddress.WalkingAddressAdminRequest;
import com.pje.sansomatchingwalkingmateapi.model.walkingAddress.WalkingAddressAdminResponse;
import com.pje.sansomatchingwalkingmateapi.model.walkingAddress.WalkingAddressUserFavoritesRequest;
import com.pje.sansomatchingwalkingmateapi.model.walkingAddress.WalkingAddressUserFavoritesResponse;
import com.pje.sansomatchingwalkingmateapi.repository.MemberRepository;
import com.pje.sansomatchingwalkingmateapi.repository.WalkingAddressRepository;
import com.pje.sansomatchingwalkingmateapi.service.common.ListConvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalkingAddressService {
    private final WalkingAddressRepository walkingAddressRepository;

    private final MemberRepository memberRepository;

    /**
     * [관리자] 산책장소 등록 메서드
     * @param request 산책 장소 정보 (장소, 위도, 경도)
     */
    public void setWalkingAddress(WalkingAddressAdminRequest request) {
        WalkingAddress walkingAddress = new WalkingAddress.WalkingAddressBuilder(request).build();
        walkingAddressRepository.save(walkingAddress);
    }

    /**
     * [일반유저] 전체 산책장소 리스트 메서드 (즐겨찾기 고르는 용도)
     * @return 산책 장소 리스트
     */
    public ListResult<WalkingAddressUserFavoritesResponse> getWalkingAddresses() {

        List<WalkingAddress> walkingAddresses = walkingAddressRepository.findAll();

        List<WalkingAddressUserFavoritesResponse> result = new LinkedList<>();

        walkingAddresses.forEach(walkingAddress -> {
            WalkingAddressUserFavoritesResponse addressItem = new WalkingAddressUserFavoritesResponse.WalkingAddressFavoritesResponseBuilder(walkingAddress).build();

            result.add(addressItem);
        });

        return ListConvertService.settingResult(result);
    }

    /**
     * [관리자] 산책장소 수정 메서드
     * @param walkingAddressId 산책 장소 시퀀스
     */
    public void putWalkingAddress(long walkingAddressId, WalkingAddressAdminRequest request) {
        WalkingAddress walkingAddress = walkingAddressRepository.findById(walkingAddressId).orElseThrow(CMissingDataException::new);
        walkingAddress.putWalkingAddress(request);
        walkingAddressRepository.save(walkingAddress);
    }

    /**
     * [일반유저] 나의 즐겨찾는 장소 등록 메서드
     * @param member 회원 시퀀스
     * @param request 즐겨찾기 장소 3곳
     */
    public void putMyWalkingAddressFavorites(Member member, WalkingAddressUserFavoritesRequest request) {
        member.putMyWalkingAddressFavorites(request);
        memberRepository.save(member);
    }

    /**
     * [일반유저] 나의 즐겨찾는 장소 조회 메서드
     * @param member 회원 시퀀스
     * @return 즐겨찾기 장소 3곳 리스트
     */
    public ListResult<WalkingAddressAdminResponse> getMyWalkingAddressFavorites(Member member) {
        List<WalkingAddress> walkingAddresses = walkingAddressRepository
                .findAllByIdEqualsOrIdEqualsOrId(
                        member.getWalkingAddressId1(),
                        member.getWalkingAddressId2(),
                        member.getWalkingAddressId3()
                );

        List<WalkingAddressAdminResponse> result = new LinkedList<>();

        walkingAddresses.forEach(walkingAddress -> {
            WalkingAddressAdminResponse addressItem = new WalkingAddressAdminResponse.WalkingAddressResponseBuilder(walkingAddress).build();

            result.add(addressItem);
        });

        return ListConvertService.settingResult(result);
    }


    //todo 나의 즐찾 수정 만들기(즐겨찾기 변경용)
}
