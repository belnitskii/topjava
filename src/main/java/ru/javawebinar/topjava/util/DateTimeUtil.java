package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static boolean filterDateTime(LocalDateTime lt, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
      return isBetweenClosedInterval(lt.toLocalDate(), startDate, endDate)
              && isBetweenHalfOpen(lt.toLocalTime(), startTime, endTime);
    }

    public static <T extends Comparable<? super T>> boolean isBetweenClosedInterval(T lt, T start, T end) {
        return lt.compareTo(start) >= 0 && lt.compareTo(end) <= 0;
    }

    public static <T extends Comparable<? super T>> boolean isBetweenHalfOpen(T lt, T start, T end) {
        return lt.compareTo(start) >= 0 && lt.compareTo(end) < 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate parseStartLocalDate(LocalDate localDate) {
        return localDate != null ? localDate : LocalDate.MIN;
    }

    public static LocalDate parseEndLocalDate(LocalDate localDate) {
        return localDate != null ? localDate : LocalDate.MAX;
    }

    public static LocalTime parseStartLocalTime(LocalTime localTime) {
        return localTime != null ? localTime : LocalTime.MIN;
    }

    public static LocalTime parseEndLocalTime(LocalTime localTime) {
        return localTime != null ? localTime : LocalTime.MAX;
    }

    public static LocalDate parseServletLocalDate(String localDate) {
        return !localDate.isEmpty() ? LocalDate.parse(localDate) : null;
    }

    public static LocalTime parseServletLocalTime(String localTime) {
        return !localTime.isEmpty() ? LocalTime.parse(localTime) : null;
    }
}
