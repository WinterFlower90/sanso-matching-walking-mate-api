package com.pje.sansomatchingwalkingmateapi.model.matchingUsage;

import com.pje.sansomatchingwalkingmateapi.entity.MatchingUsage;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchingListItem {
    private Long matchingUsageId;

    private Long applyMemberId;

    private Long receiveMemberId;

    private Long walkingAddressId;

    private String matchingStatus;

    private LocalDate dateBase;

    private LocalTime dateApply;

    private MatchingListItem(MatchingListItemBuilder builder) {
        this.matchingUsageId = builder.matchingUsageId;
        this.applyMemberId = builder.applyMemberId;
        this.receiveMemberId = builder.receiveMemberId;
        this.walkingAddressId = builder.walkingAddressId;
        this.matchingStatus = builder.matchingStatus;
        this.dateBase = builder.dateBase;
        this.dateApply = builder.dateApply;
    }

    public static class MatchingListItemBuilder implements CommonModelBuilder<MatchingListItem> {
        private final Long matchingUsageId;
        private final Long applyMemberId;
        private final Long receiveMemberId;
        private final Long walkingAddressId;
        private final String matchingStatus;
        private final LocalDate dateBase;
        private final LocalTime dateApply;

        public MatchingListItemBuilder(MatchingUsage matchingUsage) {
            this.matchingUsageId = matchingUsage.getId();
            this.applyMemberId = matchingUsage.getApplyMemberId();
            this.receiveMemberId = matchingUsage.getReceiveMemberId();
            this.walkingAddressId = matchingUsage.getWalkingAddressId();
            this.matchingStatus = matchingUsage.getMatchingStatus().getName();
            this.dateBase = matchingUsage.getDateBase();
            this.dateApply = matchingUsage.getDateApply();
        }

        @Override
        public MatchingListItem build() {
            return new MatchingListItem(this);
        }
    }

}
