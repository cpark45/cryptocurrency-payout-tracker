package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class EpochToDateConverter {
    
    public static String convertEpochToDate(long timestamp, String dateFormat) {

        Date date = new Date(timestamp * 1000); // Assumes timestamp is given in seconds converts to milliseconds
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("America/New_York"));

        return format.format(date);
    }
}
