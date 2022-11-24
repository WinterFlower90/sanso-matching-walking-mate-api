package com.pje.sansomatchingwalkingmateapi.lib;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CommonDate {
    public static LocalDate getNowDate() {
        LocalDateTime nowTime = LocalDateTime.now().plusHours(9);
        return LocalDate.of(nowTime.getYear(), nowTime.getMonth(), nowTime.getDayOfMonth());
    }

    public static LocalDateTime getNowTime() {
        return LocalDateTime.now().plusHours(9);
    }

    public static LocalTime getNowOnlyTime() {
        return LocalTime.now().plusHours(9);
    }

}
