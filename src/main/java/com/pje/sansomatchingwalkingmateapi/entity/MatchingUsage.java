package com.pje.sansomatchingwalkingmateapi.entity;

import com.pje.sansomatchingwalkingmateapi.enums.MatchingStatus;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import com.pje.sansomatchingwalkingmateapi.model.matchingUsage.MatchingCreateRequest;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "매칭 신청한 회원 시퀀스 받아오기위해 join")
    @Column(nullable = false)
    private Long applyMemberId;

    @ApiModelProperty(notes = "매칭 신청 받은 회원 시퀀스 받아오기위해 join")
    @Column(nullable = false)
    private Long receiveMemberId;

    @ApiModelProperty(notes = "매칭 상태")
    @Column(nullable = false, length = 30)
    @Enumerated(value = EnumType.STRING)
    private MatchingStatus matchingStatus;

    @ApiModelProperty(notes = "매칭 신청 메세지")
    @Column(nullable = false, length = 20)
    private String applyMessage;

    @ApiModelProperty(notes = "신청한 회원 별점")
    @Column(nullable = true)
    private Float applyStarRating;

    @ApiModelProperty(notes = "신청받은 회원 별점")
    @Column(nullable = true)
    private Float receiveStarRating;

    @ApiModelProperty(notes = "신청 기준일")
    @Column(nullable = false)
    private LocalDate dateBase;

    @ApiModelProperty(notes = "신청 시간")
    @Column(nullable = false)
    private LocalTime dateApply;

    @ApiModelProperty(notes = "신청 시간")
    @Column(nullable = true)
    private LocalTime dateMatching;

    @ApiModelProperty(notes = "약속 시간")
    @Column(nullable = true)
    private LocalTime datePromise;

    @ApiModelProperty(notes = "거절 시간")
    @Column(nullable = true)
    private LocalTime dateRefuse;

    @ApiModelProperty(notes = "수정시간")
    @Column(nullable = false)
    private LocalDateTime dateUpdate;

    private MatchingUsage(MatchingUsageCreateBuilder builder) {
        this.applyMemberId = builder.applyMemberId;
        this.receiveMemberId = builder.receiveMemberId;
        this.matchingStatus = builder.matchingStatus;
        this.applyMessage = builder.applyMessage;
        this.dateBase = builder.dateBase;
        this.dateApply = builder.dateApply;
        this.dateUpdate = builder.dateUpdate;
    }

    public static class MatchingUsageCreateBuilder implements CommonModelBuilder<MatchingUsage> {
        private final Long applyMemberId;
        private final Long receiveMemberId;
        private final MatchingStatus matchingStatus;
        private final String applyMessage;
        private final LocalDate dateBase;
        private final LocalTime dateApply;
        private final LocalDateTime dateUpdate;

        public MatchingUsageCreateBuilder(Member member, MatchingCreateRequest request) {
            this.applyMemberId = member.getId();
            this.receiveMemberId = member.getId();
            this.matchingStatus = MatchingStatus.STAND_BY;
            this.applyMessage = request.getApplyMessage();
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
