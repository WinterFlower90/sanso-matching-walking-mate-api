package com.pje.sansomatchingwalkingmateapi.model.pet;

import com.pje.sansomatchingwalkingmateapi.entity.Pet;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PetListItem {

    @ApiModelProperty(notes = "펫 시퀀스")
    private Long id;

    @ApiModelProperty(notes = "회원 시퀀스")
    private Long memberId;

    @ApiModelProperty(notes = "펫 프로필 사진")
    private String petProfileImg;

    @ApiModelProperty(notes = "펫 이름")
    private String petName;

    @ApiModelProperty(notes = "펫의 종 분류")
    private String petType;

    @ApiModelProperty(notes = "펫 성격 1")
    private String valuesTypePet1;

    @ApiModelProperty(notes = "펫 성격 2")
    private String valuesTypePet2;

    @ApiModelProperty(notes = "펫 성격 3")
    private String valuesTypePet3;

    @ApiModelProperty(notes = "펫 성격 4")
    private String valuesTypePet4;

    @ApiModelProperty(notes = "등록시간")
    private LocalDateTime dateCreate;

    @ApiModelProperty(notes = "수정 시간")
    private LocalDateTime dateUpdate;
    // flutter에서 등록시간 수정시간 안보여주고싶으면 빼면되니까 우선 통채로 get


    public PetListItem(PetListItemBuilder builder) {
        this.id = builder.id;
        this.memberId = builder.memberId;
        this.petProfileImg = builder.petProfileImg;
        this.petName = builder.petName;
        this.petType = builder.petType;
        this.valuesTypePet1 = builder.valuesTypePet1;
        this.valuesTypePet2 = builder.valuesTypePet2;
        if (valuesTypePet2 == null) return;
        this.valuesTypePet3 = builder.valuesTypePet3;
        this.valuesTypePet4 = builder.valuesTypePet4;
        this.dateCreate = builder.dateCreate;
        this.dateUpdate = builder.dateUpdate;
    }

    public static class PetListItemBuilder implements CommonModelBuilder<PetListItem> {
        private final Long id;
        private final Long memberId;
        private final String petProfileImg;
        private final String petName;
        private final String petType;
        private final String valuesTypePet1;
        private final String valuesTypePet2;
        private final String valuesTypePet3;
        private final String valuesTypePet4;
        private final LocalDateTime dateCreate;
        private final LocalDateTime dateUpdate;

        public PetListItemBuilder(Pet pet) {
            this.id = pet.getId();
            this.memberId = pet.getMember().getId();
            this.petProfileImg = pet.getPetProfileImg();
            this.petName = pet.getPetName();
            this.petType = pet.getPetType().getName();
            this.valuesTypePet1 = pet.getValuesTypePet1().getName();
            this.valuesTypePet2 = pet.getValuesTypePet2().getName();
            this.valuesTypePet3 = pet.getValuesTypePet3().getName();
            this.valuesTypePet4 = pet.getValuesTypePet4().getName();
            this.dateCreate = pet.getDateCreate();
            this.dateUpdate = pet.getDateUpdate();
        }

        @Override
        public PetListItem build() {
            return new PetListItem(this);
        }
    }

}

