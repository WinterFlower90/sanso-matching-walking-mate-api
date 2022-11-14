package com.pje.sansomatchingwalkingmateapi.entity;

import com.pje.sansomatchingwalkingmateapi.interfaces.CommonModelBuilder;
import com.pje.sansomatchingwalkingmateapi.model.notice.NoticeCreateRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "공지사항 제목")
    @Column(nullable = false, length = 20)
    private String title;

    @ApiModelProperty(notes = "공지사항 내용")
    @Column(nullable = false) //length 지정 안할시 255자.
    private String note;

    @ApiModelProperty(notes = "공지사항 작성자")
    @Column(nullable = false, length = 20)
    private String writer;

    @ApiModelProperty(notes = "공지 유무")
    @Column(nullable = false) //기본값 true. 공지에서 내리고 싶을때 false
    private boolean isEnable;

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

    private Notice(NoticeBuilder builder) {
        this.title = builder.title;
        this.note = builder.note;
        this.writer = builder.writer;
        this.isEnable = builder.isEnable;
        this.dateCreate = builder.dateCreate;
        this.dateUpdate = builder.dateUpdate;
        this.uploadFile = builder.uploadFile;
        this.fileName = builder.fileName;
        this.dateUpload = builder.dateUpload;
    }

    public static class NoticeBuilder implements CommonModelBuilder<Notice> {
        private final String title;
        private final String note;
        private final String writer;
        private final boolean isEnable;
        private final LocalDateTime dateCreate;
        private final LocalDateTime dateUpdate;
        private String uploadFile;
        private String fileName;
        private LocalDateTime dateUpload;


        public NoticeBuilder(NoticeCreateRequest request) {
            this.title = request.getTitle();
            this.note = request.getNote();
            this.writer = request.getWriter();
            this.isEnable = true;
            this.dateCreate = LocalDateTime.now();
            this.dateUpdate = LocalDateTime.now();
            this.uploadFile = request.getUploadFile();
            this.fileName = request.getFileName();
            this.dateUpload = dateUpload;

            if (uploadFile != null) {
                this.dateUpload = LocalDateTime.now();
            } else {
                this.dateUpload = null;
            }
        }

        @Override
        public Notice build() {
            return new Notice(this);
        }
    }
}
