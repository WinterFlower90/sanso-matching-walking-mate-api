package com.pje.sansomatchingwalkingmateapi.entity;

import com.pje.sansomatchingwalkingmateapi.enums.NoticeIsEnable;
import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import com.pje.sansomatchingwalkingmateapi.model.notice.NoticeCreateRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "회원 이름 받아오기위해 join")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @ApiModelProperty(notes = "공지사항 제목")
    @Column(nullable = false, length = 20)
    private String title;

    @ApiModelProperty(notes = "공지사항 내용")
    @Column(nullable = false) //length 지정 안할시 255자.
    private String note;

    @ApiModelProperty(notes = "공지사항 작성자")
    @Column(nullable = false, length = 20)
    private String writer;


    @ApiModelProperty(notes = "공지 유/무")
    @Column(nullable = false) //기본값 true. 공지에서 내리고 싶을때 false
    private Boolean noticeIsEnable;

    @ApiModelProperty(notes = "게시 날짜")
    @Column(nullable = false)
    private LocalDate datePost;

    @ApiModelProperty(notes = "등록시간")
    @Column(nullable = false)
    private LocalDateTime dateCreate;

    @ApiModelProperty(notes = "수정시간")
    @Column(nullable = false)
    private LocalDateTime dateUpdate;

    @ApiModelProperty(notes = "첨부파일")
    @Column(nullable = true)
    private String uploadFile;

    @ApiModelProperty(notes = "첨부 파일 이름")
    @Column(nullable = true)
    private String fileName;

    @ApiModelProperty(notes = "업로드시간")
    @Column(nullable = true)
    private LocalDateTime dateUpload;

    //공지에서 내리기
    public void putNoticeEnable() {
        this.noticeIsEnable = false;
        this.dateUpdate = LocalDateTime.now();
    }

    //공지사항 공지/해제 하기
    public void putNoticeEnable(NoticeIsEnable noticeIsEnable) {
        switch (noticeIsEnable) {
            case NOTICE_ENABLE:
                this.noticeIsEnable = true;
                this.dateUpdate = LocalDateTime.now();
                break;
            case NOTICE_DISABLE:
                this.noticeIsEnable = false;
                this.dateUpdate = LocalDateTime.now();
                break;
        }
    }

    //공지사항 수정
    public void putNotice(NoticeCreateRequest request) {
        this.title = request.getTitle();
        this.note = request.getNote();
        this.noticeIsEnable = true;
        this.dateUpdate = LocalDateTime.now();
//        this.uploadFile = request.getUploadFile();
//        this.fileName = request.getFileName();
        this.dateUpload = dateUpload;

        if (uploadFile != null) {
            this.dateUpload = LocalDateTime.now();
        } else {
            this.dateUpload = null;
        }
    }

    private Notice(NoticeBuilder builder) {
        this.member = builder.member;
        this.title = builder.title;
        this.note = builder.note;
        this.writer = builder.writer;
        this.noticeIsEnable = builder.noticeIsEnable;
        this.datePost = builder.datePost;
        this.dateCreate = builder.dateCreate;
        this.dateUpdate = builder.dateUpdate;
//        this.uploadFile = builder.uploadFile;
//        this.fileName = builder.fileName;
        this.dateUpload = builder.dateUpload;
    }

    public static class NoticeBuilder implements CommonModelBuilder<Notice> {
        private final Member member;
        private final String title;
        private final String note;
        private final String writer;
        private final boolean noticeIsEnable;
        private final LocalDate datePost;
        private final LocalDateTime dateCreate;
        private final LocalDateTime dateUpdate;
        //        private String uploadFile;
//        private String fileName;
        private LocalDateTime dateUpload;


        public NoticeBuilder(Member member, NoticeCreateRequest request) {
            this.member = member;
            this.title = request.getTitle();
            this.note = request.getNote();
            this.writer = member.getNickName();
            this.noticeIsEnable = true;
            this.datePost = LocalDate.now();
            this.dateCreate = LocalDateTime.now();
            this.dateUpdate = LocalDateTime.now();
//            this.uploadFile = request.getUploadFile();
//            this.fileName = request.getFileName();
            this.dateUpload = dateUpload;

//            if (uploadFile != null) {
//                this.dateUpload = LocalDateTime.now();
//            } else {
//                this.dateUpload = null;
//            }
        }

        @Override
        public Notice build() {
            return new Notice(this);
        }
    }
}
