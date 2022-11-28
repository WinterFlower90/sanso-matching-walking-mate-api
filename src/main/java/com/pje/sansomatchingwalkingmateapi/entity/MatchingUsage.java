package com.pje.sansomatchingwalkingmateapi.entity;

import com.pje.sansomatchingwalkingmateapi.enums.MatchingStatus;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import com.pje.sansomatchingwalkingmateapi.model.matchingUsage.MatchingCreateRequest;
import com.pje.sansomatchingwalkingmateapi.model.matchingUsage.MyMatchingAcceptRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.pje.sansomatchingwalkingmateapi.lib.CommonDate.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchingUsage {
    @ApiModelProperty(value = "매칭내역 시퀀스")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "회원 시퀀스", notes = "매칭 신청한 회원 시퀀스 받아오기위해 join")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applyMemberId")
    private Member applyMemberId;

    @ApiModelProperty(value = "회원 시퀀스", notes = "매칭 신청 받은 회원 시퀀스 받아오기위해 join")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiveMemberId")
    private Member receiveMemberId;


    @ApiModelProperty(notes = "산책장소 시퀀스")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "walkingAddressId")
    private WalkingAddress walkingAddressId;


    @ApiModelProperty(notes = "매칭 상태")
    @Column(nullable = false, length = 30)
    @Enumerated(value = EnumType.STRING)
    private MatchingStatus matchingStatus;

    @ApiModelProperty(notes = "수락 메세지")
    @Column(length = 20)
    private String acceptMessage;

    @ApiModelProperty(notes = "신청한 회원 별점")
    private Float applyStarRating;

    @ApiModelProperty(notes = "신청받은 회원 별점")
    private Float receiveStarRating;

    @ApiModelProperty(notes = "신청 기준일")
    @Column(nullable = false)
    private LocalDate dateBase;

    @ApiModelProperty(notes = "신청 시간")
    @Column(nullable = false)
    private LocalTime dateApply;

    @ApiModelProperty(notes = "매칭 시간")
    private LocalTime dateMatching;

    @ApiModelProperty(notes = "약속 시간")
    private LocalTime datePromise;

    @ApiModelProperty(notes = "거절 시간")
    private LocalTime dateRefuse;

    @ApiModelProperty(notes = "수정시간")
    @Column(nullable = false)
    private LocalDateTime dateUpdate;

    public void putAccept(MyMatchingAcceptRequest request) {
        this.dateMatching = getNowOnlyTime();
        this.datePromise = request.getDatePromise();
        this.dateUpdate = getNowTime();
        this.acceptMessage = request.getAcceptMessage();
        this.matchingStatus = MatchingStatus.MATCHING_BEFORE_REAL_MEETING;
    }

    private MatchingUsage(MatchingUsageCreateBuilder builder) {
        this.applyMemberId = builder.applyMemberId;
        this.receiveMemberId = builder.receiveMemberId;
        this.walkingAddressId = builder.walkingAddressId;
        this.matchingStatus = builder.matchingStatus;
        this.dateBase = builder.dateBase;
        this.dateApply = builder.dateApply;
        this.dateUpdate = builder.dateUpdate;
    }

    public static class MatchingUsageCreateBuilder implements CommonModelBuilder<MatchingUsage> {
        private final Member applyMemberId;
        private final Member receiveMemberId;
        private final WalkingAddress walkingAddressId;
        private final MatchingStatus matchingStatus;
        private final LocalDate dateBase;
        private final LocalTime dateApply;
        private final LocalDateTime dateUpdate;

        public MatchingUsageCreateBuilder(Member applyMember, Member receiveMemberId, WalkingAddress walkingAddress) {
            this.applyMemberId = applyMember;
            this.receiveMemberId = receiveMemberId;
            this.walkingAddressId = walkingAddress;
            this.matchingStatus = MatchingStatus.WAIT;
            this.dateBase = getNowDate();
            this.dateApply = getNowOnlyTime();
            this.dateUpdate = getNowTime();
        }

        @Override
        public MatchingUsage build() {
            return new MatchingUsage(this);
        }
    }
}
