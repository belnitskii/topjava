package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static boolean isBetweenHalfOpen(LocalDateTime lt, LocalDate startDate,LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        LocalDateTime start = LocalDateTime.of(startDate, startTime);
        LocalDateTime end = LocalDateTime.of(endDate, endTime);
      return !lt.isBefore(start) && lt.isBefore(end);
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate parseStartLocalDate(String localDate) {
        return localDate != null && !localDate.isEmpty() ? LocalDate.parse(localDate) : LocalDate.MIN;
    }

    public static LocalDate parseEndLocalDate(String localDate) {
        return localDate != null && !localDate.isEmpty() ? LocalDate.parse(localDate) : LocalDate.MAX;
    }

    public static LocalTime parseStartLocalTime(String localTime) {
        return localTime != null && !localTime.isEmpty() ? LocalTime.parse(localTime) : LocalTime.MIN;
    }

    public static LocalTime parseEndLocalTime(String localTime) {
        return localTime != null && !localTime.isEmpty() ? LocalTime.parse(localTime) : LocalTime.MAX;
    }
}
