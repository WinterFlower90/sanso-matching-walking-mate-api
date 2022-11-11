package com.pje.sansomatchingwalkingmateapi.lib;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommonFormat {
    public static String convertDoubleToPriceText(double value) {
        DecimalFormat format = new DecimalFormat("###,###");
        return format.format(value);
    }

    public static BigDecimal convertDoubleToDecimal(double value) {
        return BigDecimal.valueOf(value).setScale(0, RoundingMode.HALF_UP);
    }

    public static String convertLocalDateToString(LocalDate date) {
        if (date == null) return "-";
        String monthKey = date.getMonthValue() < 10 ? "0" + date.getMonthValue() : String.valueOf(date.getMonthValue());
        String dayKey = date.getDayOfMonth() < 10 ? "0" + date.getDayOfMonth() : String.valueOf(date.getDayOfMonth());
        return date.getYear() + "년" + monthKey + "월" + dayKey + "일";
    }

    public static String convertLocalDateTimeToString(LocalDateTime dateTime) {
        if (dateTime == null) return "-";
        String monthKey = dateTime.getMonthValue() < 10 ? "0" + dateTime.getMonthValue() : String.valueOf(dateTime.getMonthValue());
        String dayKey = dateTime.getDayOfMonth() < 10 ? "0" + dateTime.getDayOfMonth() : String.valueOf(dateTime.getDayOfMonth());
        return dateTime.getYear() + "-" + monthKey + "-" + dayKey + " " + dateTime.getHour() + ":" + dateTime.getMinute();
    }

}
