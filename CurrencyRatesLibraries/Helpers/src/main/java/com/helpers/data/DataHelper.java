package com.helpers.data;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class DataHelper {

    private final static String TIME_PATTERN_FULL = "yyyy-MM-dd HH:mm:ss";
    private final static String TIME_PATTERN_SHORT = "yyyy-MM-dd";
    private final static String START_TIME_PATTERN = " 00:00:00";
    private final static String FINISH_TIME_PATTERN = " 23:59:59";

    public String getCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(TIME_PATTERN_SHORT));
    }

    public Date getStartDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_PATTERN_FULL);
        try {
            return format.parse(date + START_TIME_PATTERN);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public Date getFinishDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_PATTERN_FULL);
        try {
            return format.parse(date + FINISH_TIME_PATTERN);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}