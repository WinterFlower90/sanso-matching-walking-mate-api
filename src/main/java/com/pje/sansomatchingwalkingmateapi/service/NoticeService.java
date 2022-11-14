package com.pje.sansomatchingwalkingmateapi.service;

import com.pje.sansomatchingwalkingmateapi.entity.Notice;
import com.pje.sansomatchingwalkingmateapi.model.notice.NoticeCreateRequest;
import com.pje.sansomatchingwalkingmateapi.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    /** 공지사항 등록
     * @param request 항목 값 입력
     */
    public void setNotice(NoticeCreateRequest request) {
        Notice notice = new Notice.NoticeBuilder(request).build();
        noticeRepository.save(notice);
    }
}
