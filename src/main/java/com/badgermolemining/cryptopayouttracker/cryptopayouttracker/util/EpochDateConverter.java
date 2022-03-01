package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.constants.Constants;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.UnixTimestamp.EpochStartEndDay;

public class EpochDateConverter {
    
    public static String convertEpochToDate(long timestamp, String dateFormat) {

        Date date = new Date(timestamp * Constants.ONE_THOUSAND); // Assumes timestamp is given in seconds converts to milliseconds
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.US);
        format.setTimeZone(TimeZone.getTimeZone(Constants.UTC));

        return format.format(date);
    }

    public static EpochStartEndDay getEpochStartEndDay(long timestamp) {

        EpochStartEndDay epochStartEndDay = new EpochStartEndDay();

        Date date = new Date(timestamp * Constants.ONE_THOUSAND);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(Constants.UTC));
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        Calendar startDayCalendar = Calendar.getInstance(TimeZone.getTimeZone(Constants.UTC));
        Calendar endDayCalendar = Calendar.getInstance(TimeZone.getTimeZone(Constants.UTC));

        startDayCalendar.set(year, month, day, Constants.ZERO, Constants.ZERO, Constants.ZERO);
        endDayCalendar.set(year, month, day, Constants.TWENTY_THREE, Constants.FIFTY_NINE, Constants.FIFTY_NINE);

        epochStartEndDay.setStartDayEpoch(String.valueOf(startDayCalendar.getTimeInMillis() / Constants.ONE_THOUSAND));
        epochStartEndDay.setEndDayEpoch(String.valueOf(endDayCalendar.getTimeInMillis() / Constants.ONE_THOUSAND));

        return epochStartEndDay;
    }
}
