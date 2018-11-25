package be.artisjaap.core.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class LocalDateUtils {
    private  static DateTimeFormatter dateFormatYYYYMMDD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private  static DateTimeFormatter dateFormatDDMMYYYY = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final DateTimeFormatter DATE_FORMATTED_DDMMYYYY_MET_SLASHES = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter DATE_FORMAT_YYYYMMDDHHMMSS = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private static Clock clock = Clock.systemDefaultZone();

    public static void useFixedDate(LocalDate date) {
        clock = Clock.fixed(Instant.from(date.atStartOfDay()), ZoneId.systemDefault());
    }

    public static void useFixedDate(LocalDateTime dateTime) {
        clock = Clock.fixed(Instant.from(dateTime), ZoneId.systemDefault());
    }

    public static void resetFixedDate() {
        clock = Clock.systemDefaultZone();
    }

    public static YearMonth nowYearMonth() {
        return YearMonth.now(clock);
    }

    public static String nowYearMonthAsString() {
        return nowYearMonth().toString();
    }

    public static LocalDateTime now() {
        return LocalDateTime.now(clock);
    }

    public static YearMonth parseYearMonthFromYYYYMMString(String yearMonth) {
        return YearMonth.parse(yearMonth);
    }

    public static LocalDate parseDateFromYYYYMMDDString(String date) {
        return LocalDate.parse(date, dateFormatYYYYMMDD);
    }

    public static LocalDate parseDateFromDDMMYYYYString(String date) {
        return LocalDate.parse(date, dateFormatDDMMYYYY);
    }

    public static boolean timestampInDay(LocalDateTime timestamp, LocalDate date){
        return timestamp.isAfter(date.atStartOfDay()) && timestamp.isBefore(date.plusDays(1).atStartOfDay());
    }

    public static boolean timestampInMonth(LocalDateTime timestamp, YearMonth month){
        LocalDateTime firstOfMonth = month.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = firstOfMonth.plusMonths(1).minusSeconds(1);
        return timestamp.isAfter(firstOfMonth) && timestamp.isBefore(endOfMonth);
    }

    public static String format(LocalDate localDate, DateTimeFormatter format) {
        return localDate.format(format);
    }


    public static String format(LocalDateTime localDate, DateTimeFormatter format) {
        return localDate.format(format);
    }

    public static String formatIsoDate(LocalDate localDate) {
        return format(localDate, dateFormatYYYYMMDD);
    }


}
