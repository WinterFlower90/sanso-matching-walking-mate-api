package com.pje.sansomatchingwalkingmateapi.service;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.entity.Pet;
import com.pje.sansomatchingwalkingmateapi.exception.CMissingDataException;
import com.pje.sansomatchingwalkingmateapi.model.common.ListResult;
import com.pje.sansomatchingwalkingmateapi.model.pet.PetCreateRequest;
import com.pje.sansomatchingwalkingmateapi.model.pet.PetInfoUpdateRequest;
import com.pje.sansomatchingwalkingmateapi.model.pet.PetListItem;
import com.pje.sansomatchingwalkingmateapi.model.pet.PetNameUpdateRequest;
import com.pje.sansomatchingwalkingmateapi.repository.PetRepository;
import com.pje.sansomatchingwalkingmateapi.service.common.ListConvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    /**
     * [회원용] 펫 등록
     * @param member 회원 시퀀스
     * @param request 펫 등록 폼
     */
    public void setPet(Member member, PetCreateRequest request) {
        Pet pet = new Pet.PetBuilder(member, request).build();
//        if (request.getValuesTypePet1().equals(request.getValuesTypePet2())
//                && request.getValuesTypePet2().equals(request.getValuesTypePet3())
//                && request.getValuesTypePet3().equals(request.getValuesTypePet4()));
//
//                중복체크 안되게끔하려고 시도했던 흔적
//                (flutter에서 똑같은거 선택할 수 있게 안보여주면 될지도..)
        petRepository.save(pet);
    }


    /**
     * [회원용] 펫 한마리씩 정보 불러오기
     * @param member 회원 시퀀스
     * @param id 펫 시퀀스
     * @return 펫 한마리 정보
     */
    public PetListItem getPetOne(Member member, long id) {
        Pet pet = petRepository.findByMember_IdAndId(member.getId(), id).orElseThrow(CMissingDataException::new);
        return new PetListItem.PetListItemBuilder(pet).build();
    }

    /**
     * [회원용] 펫 리스트 전부 불러오기
     * @return 펫 리스트
     */
    public ListResult<PetListItem> getPetsAll(Member member) {
        List<Pet> pets = petRepository.findByMember_Id(member.getId());
        List<PetListItem> result = new LinkedList<>();

        pets.forEach(pet -> {
            PetListItem addItem = new PetListItem.PetListItemBuilder(pet).build();
            result.add(addItem);
        });

        if (result.isEmpty()) {
            return (ListResult<PetListItem>) Collections.emptyList();
        }
        return ListConvertService.settingResult(result);
    }

    /**
     * [회원용] 펫 이름 수정하기
     * @param member 회원 시퀀스
     * @param id 펫 시퀀스
     * @param request 수정 폼
     */
    public void putPetsName(Member member, long id, PetNameUpdateRequest request) {
        Pet pet =  petRepository.findByMember_IdAndId(member.getId(), id).orElseThrow(CMissingDataException::new);
        pet.putPetName(request);
        petRepository.save(pet);
    }

    /**
     * [회원용] 펫 전체 정보 수정하기
     * @param member 회원 시퀀스
     * @param id 펫 시퀀스
     * @param request 수정 폼
     */
    public void putPetsInfo(Member member, long id, PetInfoUpdateRequest request) {
        Pet pet = petRepository.findByMember_IdAndId(member.getId(), id).orElseThrow(CMissingDataException::new);
        pet.putPetInfo(request);
        petRepository.save(pet);
    }
}

