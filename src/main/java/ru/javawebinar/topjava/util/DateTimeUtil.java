package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static boolean isBetweenHalfOpen(LocalDateTime lt, LocalDate startDate,LocalTime startTime, LocalDate endDate, LocalTime endTime) {
      return isBetweenHalfOpen(lt.toLocalDate(), startDate, endDate)
              && isBetweenHalfOpen(lt.toLocalTime(), startTime, endTime);
    }

    public static boolean isBetweenHalfOpen(LocalDate lt, LocalDate startDate, LocalDate endDate) {
        return !lt.isBefore(startDate) && !lt.isAfter(endDate);
    }

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return !lt.isBefore(startTime) && lt.isBefore(endTime);
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
