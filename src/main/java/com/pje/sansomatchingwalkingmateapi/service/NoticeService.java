package com.pje.sansomatchingwalkingmateapi.service;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.entity.Notice;
import com.pje.sansomatchingwalkingmateapi.enums.NoticeIsEnable;
import com.pje.sansomatchingwalkingmateapi.exception.CMissingDataException;
import com.pje.sansomatchingwalkingmateapi.model.common.ListResult;
import com.pje.sansomatchingwalkingmateapi.model.notice.NoticeCreateRequest;
import com.pje.sansomatchingwalkingmateapi.model.notice.NoticeListHaveNoteItem;
import com.pje.sansomatchingwalkingmateapi.model.notice.NoticeListItem;
import com.pje.sansomatchingwalkingmateapi.model.notice.NoticeSearchRequest;
import com.pje.sansomatchingwalkingmateapi.repository.NoticeRepository;
import com.pje.sansomatchingwalkingmateapi.service.common.ListConvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    /** 공지사항 등록
     * @param member 회원 엔티티 받는다
     * @param request 항목 값 입력
     */
    public void setNotice(Member member, NoticeCreateRequest request) {
        Notice notice = new Notice.NoticeBuilder(member, request).build();
        noticeRepository.save(notice);

    }

    /** 공지사항 수정
     * @param id 공지사항 시퀀스
     * @param request 항목 값 입력
     */
    public void putNotice(long id, NoticeCreateRequest request) {
        Notice notice = noticeRepository.findById(id).orElseThrow(CMissingDataException::new);
        notice.putNotice(request);
        noticeRepository.save(notice);
    }

    /**
     * 공지사항 공지 or 해제
     * @param id 공지사항 시퀀스
     * @param noticeIsEnable 공지 or 해제
     */

    public void putNoticeEnable(long id, NoticeIsEnable noticeIsEnable)  {
        Notice notice = noticeRepository.findById(id).orElseThrow(CMissingDataException::new);
        notice.putNoticeEnable(noticeIsEnable);
        noticeRepository.save(notice);
    }


    /** 공지사항 리스트 가져오기
     * 유효한 게시물 + 기간필터를 이용
     * @param pageNum 페이지 숫자
     * @param noticeSearchRequest request 항목 값 입력받음. (년, 월)
     * @return 해당 항목을 리스트 형태로 반환
     */
    public ListResult<NoticeListItem> getEnableNoticeList(int pageNum, NoticeSearchRequest noticeSearchRequest) {
        PageRequest pageRequest = ListConvertService.getPageable(pageNum, 10);

        LocalDate dateStart = LocalDate.of(noticeSearchRequest.getDateYear(), noticeSearchRequest.getDateMonth(), 1);
        Calendar calendar = Calendar.getInstance();
        calendar.set(noticeSearchRequest.getDateYear(), noticeSearchRequest.getDateMonth() - 1, 1);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        LocalDate dateEnd = LocalDate.of(noticeSearchRequest.getDateYear(), noticeSearchRequest.getDateMonth(), maxDay);

        Page<Notice> notices = noticeRepository.findAllByNoticeIsEnableAndDatePostGreaterThanEqualAndDatePostLessThanEqualOrderByIdDesc(true, dateStart, dateEnd, pageRequest);
        List<NoticeListItem> result = new LinkedList<>();

        notices.forEach(notice -> {
            NoticeListItem addItem = new NoticeListItem.NoticeListItemBuilder(notice).build();
            result.add(addItem);
        });

        return ListConvertService.settingResult(result, notices.getTotalElements(), notices.getTotalPages(), notices.getPageable().getPageNumber());
    }

    /** 공지사항 전체리스트 가져오기
     *
     * @return 전체 항목 리스트 반환
     */
    public ListResult<NoticeListItem> getNoticeList() {
        List<Notice> notices = noticeRepository.findAll();
        List<NoticeListItem> result = new LinkedList<>();

        notices.forEach(notice -> {
            NoticeListItem addItem = new NoticeListItem.NoticeListItemBuilder(notice).build();
            result.add(addItem);
        });

        return ListConvertService.settingResult(result);
    }

    public NoticeListHaveNoteItem getNoticeOne(long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(CMissingDataException::new);
        return new NoticeListHaveNoteItem.NoticeListHaveNoteItemBuilder(notice).build();
    }

    /** 공지사항 전체리스트(내용 포함) 가져오기
     *
     * @return 전체 항목 리스트 반환
     */
    public ListResult<NoticeListHaveNoteItem> getNoticeNoteList() {
        List<Notice> notices = noticeRepository.findAll();
        List<NoticeListHaveNoteItem> result = new LinkedList<>();

        notices.forEach(notice -> {
            NoticeListHaveNoteItem addItem = new NoticeListHaveNoteItem.NoticeListHaveNoteItemBuilder(notice).build();
            result.add(addItem);
        });

        return ListConvertService.settingResult(result);
    }



    /**
     * [관리자]  페이지 + 기간별 + 제목 검색 + 공지중/해제중
     * (메서드 오버로딩 사용)
     * @param pageNum 페이지 번호
     * @param dateYear 조회 연도
     * @param dateMonth 조회 달
     * @param searchTitle 제목 검색
     * @param noticeIsEnable 공지중/해제중
     * @return 공지사항 정보
     */
    public ListResult<NoticeListItem> getSearchNoticeList(int pageNum, int dateYear, int dateMonth, String searchTitle, NoticeIsEnable noticeIsEnable) {
        PageRequest pageRequest = ListConvertService.getPageable(pageNum, 10);

        LocalDate dateStart = LocalDate.of(dateYear, dateMonth, 1);
        Calendar calendar = Calendar.getInstance();
        calendar.set(dateYear, dateMonth - 1, 1);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        LocalDate dateEnd = LocalDate.of(dateYear, dateMonth, maxDay);

        Page<Notice> notices = noticeRepository.findAllByTitleContainingIgnoreCaseAndNoticeIsEnableAndDatePostGreaterThanEqualAndDatePostLessThanEqualOrderByIdDesc(searchTitle, noticeIsEnable.getType(), dateStart, dateEnd, pageRequest);
        List<NoticeListItem> result = new LinkedList<>();

        notices.forEach(notice -> {
            NoticeListItem addItem = new NoticeListItem.NoticeListItemBuilder(notice).build();
            result.add(addItem);
        });

        return ListConvertService.settingResult(result, notices.getTotalElements(), notices.getTotalPages(), notices.getPageable().getPageNumber());
    }

    /**
     * [관리자]  페이지 + 기간별 + 제목 검색
     * (메서드 오버로딩 사용)
     * @param pageNum 페이지 번호
     * @param dateYear 조회 연도
     * @param dateMonth 조회 달
     * @param searchTitle 제목 검색
     * @return 공지사항 정보
     */
    public ListResult<NoticeListItem> getSearchNoticeList(int pageNum, int dateYear, int dateMonth, String searchTitle) {
        PageRequest pageRequest = ListConvertService.getPageable(pageNum, 10);

        LocalDate dateStart = LocalDate.of(dateYear, dateMonth, 1);
        Calendar calendar = Calendar.getInstance();
        calendar.set(dateYear, dateMonth - 1, 1);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        LocalDate dateEnd = LocalDate.of(dateYear, dateMonth, maxDay);

        Page<Notice> notices = noticeRepository.findAllByTitleContainingIgnoreCaseAndDatePostGreaterThanEqualAndDatePostLessThanEqualOrderByNoticeIsEnableDescIdDesc(searchTitle, dateStart, dateEnd, pageRequest);
        List<NoticeListItem> result = new LinkedList<>();

        notices.forEach(notice -> {
            NoticeListItem addItem = new NoticeListItem.NoticeListItemBuilder(notice).build();
            result.add(addItem);
        });

        return ListConvertService.settingResult(result, notices.getTotalElements(), notices.getTotalPages(), notices.getPageable().getPageNumber());
    }


}