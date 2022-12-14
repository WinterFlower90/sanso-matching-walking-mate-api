package com.pje.sansomatchingwalkingmateapi.entity;

import com.pje.sansomatchingwalkingmateapi.enums.keyword.ValuesTypeFriend;
import com.pje.sansomatchingwalkingmateapi.enums.keyword.ValuesTypeWalking;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import com.pje.sansomatchingwalkingmateapi.model.keyword.KeywordCreateRequest;
import com.pje.sansomatchingwalkingmateapi.model.keyword.KeywordFriendIWantUpdateRequest;
import com.pje.sansomatchingwalkingmateapi.model.keyword.KeywordFriendYouWantUpdateRequest;
import com.pje.sansomatchingwalkingmateapi.model.keyword.KeywordWalkingUpdateRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "회원 시퀀스 받아오기위해 join")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;


    //

    @ApiModelProperty(notes = "산책관(필수)")
    @Column(nullable = false, length = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeWalking valuesTypeWalking1;

    @ApiModelProperty(notes = "산책관(필수)")
    @Column(nullable = false, length = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeWalking valuesTypeWalking2;

    @ApiModelProperty(notes = "산책관(필수)")
    @Column(nullable = false, length = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeWalking valuesTypeWalking3;

    @ApiModelProperty(notes = "산책관(선택)")
    @Column(nullable = true, length = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeWalking valuesTypeWalking4;

    @ApiModelProperty(notes = "산책관(선택)")
    @Column(nullable = true, length = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeWalking valuesTypeWalking5;

    @ApiModelProperty(notes = "산책관(선택)")
    @Column(nullable = true, length = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeWalking valuesTypeWalking6;

    //

    @ApiModelProperty(notes = "원하는 친구상(필수)")
    @Column(nullable = false, length = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendIWant1;

    @ApiModelProperty(notes = "원하는 친구상(필수)")
    @Column(nullable = false, length = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendIWant2;

    @ApiModelProperty(notes = "원하는 친구상(필수)")
    @Column(nullable = false, length = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendIWant3;

    @ApiModelProperty(notes = "원하는 친구상(선택)")
    @Column(nullable = true, length = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendIWant4;

    @ApiModelProperty(notes = "원하는 친구상(선택)")
    @Column(nullable = true, length = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendIWant5;

    @ApiModelProperty(notes = "원하는 친구상(선택)")
    @Column(nullable = true, length = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendIWant6;

    //

    @ApiModelProperty(notes = "내가 될 친구상(필수)")
    @Column(nullable = false, length = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendYouWant1;

    @ApiModelProperty(notes = "내가 될 친구상(필수)")
    @Column(nullable = false, length = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendYouWant2;

    @ApiModelProperty(notes = "내가 될 친구상(필수)")
    @Column(nullable = false, length = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendYouWant3;

    @ApiModelProperty(notes = "내가 될 친구상(선택)")
    @Column(nullable = true, length = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendYouWant4;

    @ApiModelProperty(notes = "내가 될 친구상(선택)")
    @Column(nullable = true, length = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendYouWant5;

    @ApiModelProperty(notes = "내가 될 친구상(선택)")
    @Column(nullable = true, length = 30)
    @Enumerated(value = EnumType.STRING)
    private ValuesTypeFriend valuesTypeFriendYouWant6;

    @ApiModelProperty(notes = "등록시간")
    @Column(nullable = false)
    private LocalDateTime dateCreate;

    @ApiModelProperty(notes = "수정시간")
    @Column(nullable = false)
    private LocalDateTime dateUpdate;


    public void putKeywordWalking(KeywordWalkingUpdateRequest updateRequest) {
        this.valuesTypeWalking1 = updateRequest.getValuesTypeWalking1();
        this.valuesTypeWalking2 = updateRequest.getValuesTypeWalking2();
        this.valuesTypeWalking3 = updateRequest.getValuesTypeWalking3();
        this.valuesTypeWalking4 = updateRequest.getValuesTypeWalking4() == null ? ValuesTypeWalking.NONE : updateRequest.getValuesTypeWalking4();
        this.valuesTypeWalking5 = updateRequest.getValuesTypeWalking5() == null ? ValuesTypeWalking.NONE : updateRequest.getValuesTypeWalking4();
        this.valuesTypeWalking6 = updateRequest.getValuesTypeWalking6() == null ? ValuesTypeWalking.NONE : updateRequest.getValuesTypeWalking4();
        this.dateUpdate = LocalDateTime.now();
    }

    public void putKeywordFriendIWant(KeywordFriendIWantUpdateRequest updateRequest) {
        this.valuesTypeFriendIWant1 = updateRequest.getValuesTypeFriendIWant1();
        this.valuesTypeFriendIWant2 = updateRequest.getValuesTypeFriendIWant2();
        this.valuesTypeFriendIWant3 = updateRequest.getValuesTypeFriendIWant3();
        this.valuesTypeFriendIWant4 = updateRequest.getValuesTypeFriendIWant4() == null ? ValuesTypeFriend.NONE : updateRequest.getValuesTypeFriendIWant4();
        this.valuesTypeFriendIWant5 = updateRequest.getValuesTypeFriendIWant5() == null ? ValuesTypeFriend.NONE : updateRequest.getValuesTypeFriendIWant5();
        this.valuesTypeFriendIWant6 = updateRequest.getValuesTypeFriendIWant6() == null ? ValuesTypeFriend.NONE : updateRequest.getValuesTypeFriendIWant6();
        this.dateUpdate = LocalDateTime.now();
    }

    public void putKeywordFriendYouWant(KeywordFriendYouWantUpdateRequest updateRequest) {
        this.valuesTypeFriendYouWant1 = updateRequest.getValuesTypeFriendYouWant1();
        this.valuesTypeFriendYouWant2 = updateRequest.getValuesTypeFriendYouWant2();
        this.valuesTypeFriendYouWant3 = updateRequest.getValuesTypeFriendYouWant3();
        this.valuesTypeFriendYouWant4 = updateRequest.getValuesTypeFriendYouWant4() == null ? ValuesTypeFriend.NONE : updateRequest.getValuesTypeFriendYouWant4();
        this.valuesTypeFriendYouWant5 = updateRequest.getValuesTypeFriendYouWant5() == null ? ValuesTypeFriend.NONE : updateRequest.getValuesTypeFriendYouWant5();
        this.valuesTypeFriendYouWant6 = updateRequest.getValuesTypeFriendYouWant6() == null ? ValuesTypeFriend.NONE : updateRequest.getValuesTypeFriendYouWant6();
        this.dateUpdate = LocalDateTime.now();
    }

    private Keyword(KeywordBuilder builder) {
        this.member = builder.member;
        this.valuesTypeWalking1 = builder.valuesTypeWalking1;
        this.valuesTypeWalking2 = builder.valuesTypeWalking2;
        this.valuesTypeWalking3 = builder.valuesTypeWalking3;
        this.valuesTypeWalking4 = builder.valuesTypeWalking4;
        this.valuesTypeWalking5 = builder.valuesTypeWalking5;
        this.valuesTypeWalking6 = builder.valuesTypeWalking6;
        this.valuesTypeFriendIWant1 = builder.valuesTypeFriendIWant1;
        this.valuesTypeFriendIWant2 = builder.valuesTypeFriendIWant2;
        this.valuesTypeFriendIWant3 = builder.valuesTypeFriendIWant3;
        this.valuesTypeFriendIWant4 = builder.valuesTypeFriendIWant4;
        this.valuesTypeFriendIWant5 = builder.valuesTypeFriendIWant5;
        this.valuesTypeFriendIWant6 = builder.valuesTypeFriendIWant6;
        this.valuesTypeFriendYouWant1 = builder.valuesTypeFriendYouWant1;
        this.valuesTypeFriendYouWant2 = builder.valuesTypeFriendYouWant2;
        this.valuesTypeFriendYouWant3 = builder.valuesTypeFriendYouWant3;
        this.valuesTypeFriendYouWant4 = builder.valuesTypeFriendYouWant4;
        this.valuesTypeFriendYouWant5 = builder.valuesTypeFriendYouWant5;
        this.valuesTypeFriendYouWant6 = builder.valuesTypeFriendYouWant6;
        this.dateCreate = builder.dateCreate;
        this.dateUpdate = builder.dateUpdate;
    }

    public static class KeywordBuilder implements CommonModelBuilder<Keyword> {
        private final Member member;
        private final ValuesTypeWalking valuesTypeWalking1;
        private final ValuesTypeWalking valuesTypeWalking2;
        private final ValuesTypeWalking valuesTypeWalking3;
        private ValuesTypeWalking valuesTypeWalking4;
        private ValuesTypeWalking valuesTypeWalking5;
        private ValuesTypeWalking valuesTypeWalking6;
        private final ValuesTypeFriend valuesTypeFriendIWant1;
        private final ValuesTypeFriend valuesTypeFriendIWant2;
        private final ValuesTypeFriend valuesTypeFriendIWant3;
        private ValuesTypeFriend valuesTypeFriendIWant4;
        private ValuesTypeFriend valuesTypeFriendIWant5;
        private ValuesTypeFriend valuesTypeFriendIWant6;
        private final ValuesTypeFriend valuesTypeFriendYouWant1;
        private final ValuesTypeFriend valuesTypeFriendYouWant2;
        private final ValuesTypeFriend valuesTypeFriendYouWant3;
        private ValuesTypeFriend valuesTypeFriendYouWant4;
        private ValuesTypeFriend valuesTypeFriendYouWant5;
        private ValuesTypeFriend valuesTypeFriendYouWant6;
        private final LocalDateTime dateCreate;
        private final LocalDateTime dateUpdate;

        public KeywordBuilder(Member member, KeywordCreateRequest request) {
            this.member = member;
            this.valuesTypeWalking1 = request.getValuesTypeWalking1();
            this.valuesTypeWalking2 = request.getValuesTypeWalking2();
            this.valuesTypeWalking3 = request.getValuesTypeWalking3();
            this.valuesTypeWalking4 = request.getValuesTypeWalking4() == null ? ValuesTypeWalking.NONE : request.getValuesTypeWalking4();
            this.valuesTypeWalking5 = request.getValuesTypeWalking5() == null ? ValuesTypeWalking.NONE : request.getValuesTypeWalking5();
            this.valuesTypeWalking6 = request.getValuesTypeWalking6() == null ? ValuesTypeWalking.NONE : request.getValuesTypeWalking6();
            this.valuesTypeFriendIWant1 = request.getValuesTypeFriendIWant1();
            this.valuesTypeFriendIWant2 = request.getValuesTypeFriendIWant2();
            this.valuesTypeFriendIWant3 = request.getValuesTypeFriendIWant3();
            this.valuesTypeFriendIWant4 = request.getValuesTypeFriendIWant4() == null ? ValuesTypeFriend.NONE : request.getValuesTypeFriendIWant4();
            this.valuesTypeFriendIWant5 = request.getValuesTypeFriendIWant5() == null ? ValuesTypeFriend.NONE : request.getValuesTypeFriendIWant5();
            this.valuesTypeFriendIWant6 = request.getValuesTypeFriendIWant6() == null ? ValuesTypeFriend.NONE : request.getValuesTypeFriendIWant6();
            this.valuesTypeFriendYouWant1 = request.getValuesTypeFriendYouWant1();
            this.valuesTypeFriendYouWant2 = request.getValuesTypeFriendYouWant2();
            this.valuesTypeFriendYouWant3 = request.getValuesTypeFriendYouWant3();
            this.valuesTypeFriendYouWant4 = request.getValuesTypeFriendYouWant4() == null ? ValuesTypeFriend.NONE : request.getValuesTypeFriendYouWant4();
            this.valuesTypeFriendYouWant5 = request.getValuesTypeFriendYouWant5() == null ? ValuesTypeFriend.NONE : request.getValuesTypeFriendYouWant5();
            this.valuesTypeFriendYouWant6 = request.getValuesTypeFriendYouWant6() == null ? ValuesTypeFriend.NONE : request.getValuesTypeFriendYouWant6();
            this.dateCreate = LocalDateTime.now();
            this.dateUpdate = LocalDateTime.now();
        }

        @Override
        public Keyword build() {
            return new Keyword(this);
        }
    }
}
