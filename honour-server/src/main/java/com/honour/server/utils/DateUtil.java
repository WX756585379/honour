package com.honour.server.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class DateUtil {

    public static DateTimeFormatter time_format = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static DateTimeFormatter date_format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter datetime_format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取一个月的第一天
     */
    public static String getFirstDayOfMonth(DateTimeFormatter formatter) {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = LocalDate.of(today.getYear(), today.getMonth(), 1);
        return firstDay.format(formatter);
    }

    /**
     * 获取一个月的最后一天
     */
    public static String getLastDayOfMonth(DateTimeFormatter formatter) {
        LocalDate today = LocalDate.now();
        LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());
        return lastDay.format(formatter);
    }

    public static void main(String[] args) {
        System.out.println(getFirstDayOfMonth(date_format));
        System.out.println(getLastDayOfMonth(date_format));
    }
}
