package com.pje.sansomatchingwalkingmateapi.entity;

import com.pje.sansomatchingwalkingmateapi.enums.KindOfPet;
import com.pje.sansomatchingwalkingmateapi.enums.keyword.ValuesTypePet;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import com.pje.sansomatchingwalkingmateapi.model.pet.PetCreateRequest;
import com.pje.sansomatchingwalkingmateapi.model.pet.PetInfoUpdateRequest;
import com.pje.sansomatchingwalkingmateapi.model.pet.PetNameUpdateRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    @ApiModelProperty(notes = "회원 시퀀스")
    private Member member;

    @ApiModelProperty(notes = "펫 프로필 사진")
    private String petProfileImg;

    @Column(nullable = false, length = 20)
    @ApiModelProperty(notes = "펫 이름")
    private String petName;

    @Column(nullable = false, length = 20)
    @Enumerated(value = EnumType.STRING)
    @ApiModelProperty(notes = "펫의 종 분류")
    private KindOfPet petType;

    @Column(nullable = false, length = 30)
    @Enumerated(value = EnumType.STRING)
    @ApiModelProperty(notes = "펫 성격 1")
    private ValuesTypePet valuesTypePet1;

    @Column(length = 30)
    @Enumerated(value = EnumType.STRING)
    @ApiModelProperty(notes = "펫 성격 2")
    private ValuesTypePet valuesTypePet2;

    @Column(length = 30)
    @Enumerated(value = EnumType.STRING)
    @ApiModelProperty(notes = "펫 성격 3")
    private ValuesTypePet valuesTypePet3;

    @Column(length = 30)
    @Enumerated(value = EnumType.STRING)
    @ApiModelProperty(notes = "펫 성격 4")
    private ValuesTypePet valuesTypePet4;

    @ApiModelProperty(notes = "등록시간")
    @Column(nullable = false)
    private LocalDateTime dateCreate;

    @ApiModelProperty(notes = "수정 시간")
    @Column(nullable = false)
    private LocalDateTime dateUpdate;

    public void putPetName(PetNameUpdateRequest petNameUpdate) {
        this.petName = petNameUpdate.getPetName();
    } // 혹시 몰라 이름만 수정하는 메소드 만듦

    public void putPetInfo(PetInfoUpdateRequest petInfoUpdate) {
        this.petName = petInfoUpdate.getPetName();
        this.valuesTypePet1 = petInfoUpdate.getValuesTypePet1();
        this.valuesTypePet2 =
                petInfoUpdate.getValuesTypePet2() == null ? ValuesTypePet.NONE : petInfoUpdate.getValuesTypePet2();
        this.valuesTypePet3 =
                petInfoUpdate.getValuesTypePet3() == null ? ValuesTypePet.NONE : petInfoUpdate.getValuesTypePet3();
        this.valuesTypePet4 =
                petInfoUpdate.getValuesTypePet4() == null ? ValuesTypePet.NONE : petInfoUpdate.getValuesTypePet4();

    } // 이름 + 성격

    public void putPetProfileImg() {

    }
    // 프로필사진

    private Pet(PetBuilder builder) {
        this.member = builder.member;
        this.petProfileImg = builder.petProfileImg;
        this.petName = builder.petName;
        this.petType = builder.petType;
        this.valuesTypePet1 = builder.valuesTypePet1;
        this.valuesTypePet2 = builder.valuesTypePet2;
        this.valuesTypePet3 = builder.valuesTypePet3;
        this.valuesTypePet4 = builder.valuesTypePet4;
        this.dateCreate = builder.dateCreate;
        this.dateUpdate = builder.dateUpdate;
    }

    public static class PetBuilder implements CommonModelBuilder<Pet> {
        private final Member member;
        private final String petProfileImg;
        private final String petName;
        private final KindOfPet petType;
        private final ValuesTypePet valuesTypePet1;
        private final ValuesTypePet valuesTypePet2; // 우선 다 final
        private final ValuesTypePet valuesTypePet3;
        private final ValuesTypePet valuesTypePet4;
        private final LocalDateTime dateCreate;
        private final LocalDateTime dateUpdate;


        public PetBuilder(Member member, PetCreateRequest request) {
            this.member = member;
            this.petProfileImg = request.getPetProfileImg();
            this.petName = request.getPetName();
            this.petType = request.getPetType();
            this.valuesTypePet1 = request.getValuesTypePet1();
            this.valuesTypePet2 =
                    request.getValuesTypePet2() == null ? ValuesTypePet.NONE : request.getValuesTypePet2(); // null이면 (선택안함)
            this.valuesTypePet3 =
                    request.getValuesTypePet3() == null ? ValuesTypePet.NONE : request.getValuesTypePet3();
            this.valuesTypePet4 =
                    request.getValuesTypePet3() == null ? ValuesTypePet.NONE : request.getValuesTypePet3();
            this.dateCreate = LocalDateTime.now();
            this.dateUpdate = LocalDateTime.now();

        }
        @Override
        public Pet build() {
            return new Pet(this);
        }
    }
}

