package be.artisjaap.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateUtils {
    private static final DateTimeFormatter dateFormatYYYYMMDD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter dateFormatDDMMYYYY = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final DateTimeFormatter DATE_FORMATTED_DDMMYYYY_MET_SLASHES = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter DATE_FORMAT_YYYYMMDDHHMMSS = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final LocalDate MIN_DATE = parseDateFromDDMMYYYYString("01-01-1900");
    public static final LocalDate MAX_DATE = parseDateFromDDMMYYYYString("31-12-9999");

    private static Clock clock = Clock.systemDefaultZone();

    private LocalDateUtils(){}

    public static void useFixedDate(LocalDate date) {
        clock = Clock.fixed(Instant.from(date.atStartOfDay()), ZoneId.systemDefault());
    }

    public static void useFixedDate(LocalDateTime dateTime) {
        clock = Clock.fixed(Instant.from(dateTime.atZone(ZoneId.systemDefault())), ZoneId.systemDefault());
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

    public static LocalDate today() {
        return LocalDate.now(clock);
    }

    public static LocalDateTime startOfToday(){
        return today().atStartOfDay();
    }

    public static LocalDateTime endOfToday(){
        return startOfToday().plusDays(1).minusNanos(1);
    }



    public static YearMonth parseYearMonthFromYYYYMMString(String yearMonth) {
        return YearMonth.parse(yearMonth);
    }

    public static LocalDate parseDateFromYYYYMMDDString(String date) {
        return LocalDate.parse(date, dateFormatYYYYMMDD);
    }

    public static LocalDateTime parseDateFromYYYYMMDDString(String date, boolean startOfDay) {
        if(startOfDay) {
            return parseDateFromYYYYMMDDString(date).atStartOfDay();
        }

        return parseDateFromYYYYMMDDString(date).plusDays(1).atStartOfDay().minusNanos(1);

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

    public static String formatIsoDate(LocalDateTime localDate) {
        return format(localDate, dateFormatYYYYMMDD);
    }

    public static String nowTimestamp(){
        return format(now(), DATE_FORMAT_YYYYMMDDHHMMSS);
    }

    public static Date nowAsDate() {
        return new Date();
    }

    public static long durationInMiliseconds(LocalDateTime timestamp){
        //FIXME
        return 0;
    }

    public static String msToFormattedTime(long duration) {
        int ms = (int)duration%1000;
        int seconden = ms/1000;
        int sec = seconden%60;
        int min = seconden/60;
        return d2Format(min) + ":" + d2Format(sec) + "." + d3Format(ms);
    }

    private static String d2Format(int nr){
        return nr < 10?"0"+nr:""+nr;
    }

    private static String d3Format(int nr){
        if(nr < 10)
            return "00"+nr;

        if(nr < 100)
            return "0"+nr;

        return ""+nr;
    }

    public static LocalDateTime endOfDay(LocalDate date) {
        return date.atStartOfDay().minusNanos(1);
    }
}
