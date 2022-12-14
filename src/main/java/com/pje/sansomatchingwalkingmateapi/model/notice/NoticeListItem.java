package com.pje.sansomatchingwalkingmateapi.model.notice;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.entity.Notice;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeListItem {
    private Long noticeId;

    private String title;

    private LocalDate datePost;

    private String writer;

    private String noticeIsEnable;

    private NoticeListItem(NoticeListItemBuilder builder) {
        this.noticeId = builder.noticeId;
        this.title = builder.title;
        this.datePost = builder.datePost;
        this.writer = builder.writer;
        this.noticeIsEnable = builder.noticeIsEnable;
    }

    public static class NoticeListItemBuilder implements CommonModelBuilder<NoticeListItem> {
        private final Long noticeId;
        private final String title;
        private final LocalDate datePost;
        private final String writer;
        private final String noticeIsEnable;

        public NoticeListItemBuilder(Notice notice) {
            this.noticeId = notice.getId();
            this.title = notice.getTitle();
            this.datePost = notice.getDatePost();
            this.writer = notice.getWriter();
            this.noticeIsEnable = notice.getNoticeIsEnable() ? "공지중" : "해제중" ;
        }

        @Override
        public NoticeListItem build() {
            return new NoticeListItem(this);
        }
    }

}
