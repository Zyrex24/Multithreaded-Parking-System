package io;

import java.lang.String;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public static void log(String message) {
        LocalDateTime now = LocalDateTime.now();

        // Format to display only minutes and seconds
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss");
        String formattedTime = now.format(formatter);
        String space = " - ";
        System.out.println(formattedTime + space + message);
    }
}
