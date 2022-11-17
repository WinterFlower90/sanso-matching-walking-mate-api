package com.pje.sansomatchingwalkingmateapi.controller;


import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.model.common.CommonResult;
import com.pje.sansomatchingwalkingmateapi.model.common.ListResult;
import com.pje.sansomatchingwalkingmateapi.model.common.SingleResult;
import com.pje.sansomatchingwalkingmateapi.model.pet.PetCreateRequest;
import com.pje.sansomatchingwalkingmateapi.model.pet.PetInfoUpdateRequest;
import com.pje.sansomatchingwalkingmateapi.model.pet.PetListItem;
import com.pje.sansomatchingwalkingmateapi.model.pet.PetNameUpdateRequest;
import com.pje.sansomatchingwalkingmateapi.service.PetService;
import com.pje.sansomatchingwalkingmateapi.service.common.ProfileService;
import com.pje.sansomatchingwalkingmateapi.service.common.ResponseService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "펫 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/pet")
public class PetController {

    private final PetService petService;
    private final ProfileService profileService;

    @ApiModelProperty(value = "나의 펫 등록")
    @PostMapping("/new")
    public CommonResult setPet(@RequestBody @Valid PetCreateRequest request) {
        Member member = profileService.getMemberData();
        petService.setPet(member, request);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "나의 펫 전체 목록 가져오기")
    @GetMapping("/all")
    public ListResult getPetsAll() {
        return ResponseService.getListResult(petService.getPetsAll(profileService.getMemberData()), true);
    }

    @ApiOperation(value = "나의 각 펫의 정보 (한마리씩) 가져오기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "펫 시퀀스", required = true)
    })
    @GetMapping("/one/pet-id/{id}")
    public SingleResult<PetListItem> getPetOne(@PathVariable long id) {
        return ResponseService.getSingleResult(petService.getPetOne(profileService.getMemberData(), id));
    }

    @ApiOperation(value = "나의 각 펫 이름 수정하기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "펫 시퀀스", required = true)
    })
    @PutMapping("/put-pet-name/pet-id/{id}")
    public CommonResult putPetsName(@PathVariable long id, @RequestBody @Valid PetNameUpdateRequest request) {
        Member member = profileService.getMemberData();
        petService.putPetsName(member, id, request);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "나의 각 펫 정보 수정하기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "펫 시퀀스", required = true)
    })
    @PutMapping("/put-pet-info/pet-id/{id}")
    public CommonResult putPetsInfo(@PathVariable long id, @RequestBody @Valid PetInfoUpdateRequest request) {
        Member member = profileService.getMemberData();
        petService.putPetsInfo(member, id, request);
        return ResponseService.getSuccessResult();
    }

}
