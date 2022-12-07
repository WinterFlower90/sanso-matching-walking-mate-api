package com.pje.sansomatchingwalkingmateapi.model.notice;

import com.pje.sansomatchingwalkingmateapi.entity.Notice;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeListHaveNoteItem {

    private Long noticeId;
    private String title;
    private String note;
    private LocalDate datePost;
    private String writer;
    private NoticeListHaveNoteItem(NoticeListHaveNoteItemBuilder builder) {
        this.noticeId = builder.noticeId;
        this.title = builder.title;
        this.note = builder.note;
        this.datePost = builder.datePost;
        this.writer = builder.writer;
    }

    public static class NoticeListHaveNoteItemBuilder implements CommonModelBuilder<NoticeListHaveNoteItem> {
        private final Long noticeId;
        private final String title;
        private final String note;
        private final LocalDate datePost;
        private final String writer;

        public NoticeListHaveNoteItemBuilder(Notice notice) {
            this.noticeId = notice.getId();
            this.title = notice.getTitle();
            this.note = notice.getNote();
            this.datePost = notice.getDatePost();
            this.writer = notice.getWriter();
        }

        @Override
        public NoticeListHaveNoteItem build() {
            return new NoticeListHaveNoteItem(this);
        }
    }
}
