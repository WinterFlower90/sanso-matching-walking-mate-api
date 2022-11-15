package com.pje.sansomatchingwalkingmateapi.repository;

import com.pje.sansomatchingwalkingmateapi.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findAllByNoticeIsEnableAndDatePostGreaterThanEqualAndDatePostLessThanEqualOrderByIdDesc(Boolean noticeIsEnable, LocalDate dateStart, LocalDate dateEnd);

}
