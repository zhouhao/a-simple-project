package me.hzhou.todo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateFormatUtils {
    private DateFormatUtils() {
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm");

    public static String format(LocalDateTime now) {
        return now.format(formatter);
    }
}
