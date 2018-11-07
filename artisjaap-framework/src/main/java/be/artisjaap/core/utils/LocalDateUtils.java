package be.artisjaap.core.utils;

import java.time.LocalDateTime;
import java.time.YearMonth;

public class LocalDateUtils {

    public static YearMonth nowYearMonth() {
        return YearMonth.now();
    }

    public static String nowYearMonthAsString() {
        return nowYearMonth().toString();
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static YearMonth parseYearMonthFromYYYYMMString(String yearMonth) {
        return YearMonth.parse(yearMonth);
    }
}
