package com.pje.sansomatchingwalkingmateapi.repository;

import com.pje.sansomatchingwalkingmateapi.entity.Member;
import com.pje.sansomatchingwalkingmateapi.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    Page<Notice> findAllByNoticeIsEnableAndDatePostGreaterThanEqualAndDatePostLessThanEqualOrderByIdDesc(Boolean noticeIsEnable, LocalDate dateStart, LocalDate dateEnd, Pageable pageable);

    Page<Notice> findAllByTitleContainingIgnoreCaseAndDatePostGreaterThanEqualAndDatePostLessThanEqualOrderByNoticeIsEnableDescIdDesc(String searchTitle, LocalDate dateStart, LocalDate dateEnd, Pageable pageable);

    Page<Notice> findAllByTitleContainingIgnoreCaseAndNoticeIsEnableAndDatePostGreaterThanEqualAndDatePostLessThanEqualOrderByIdDesc(String searchTitle, Boolean noticeIsEnable, LocalDate dateStart, LocalDate dateEnd, Pageable pageable);

}
