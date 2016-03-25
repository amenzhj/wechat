package com.here2u.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateUtil
{
    private final static String TIME_FORMAT = "yyyy-MM-dd HH:mm";
    


    public static String format(Date date)
    {
        return date == null ? " " : DateFormatUtils.format(date, TIME_FORMAT);
    }
    
    public static Date parse(String date)
        throws ParseException
    {
        return StringUtils.isBlank(date) ? null : DateUtils.parseDate(date, TIME_FORMAT);
    }

}
