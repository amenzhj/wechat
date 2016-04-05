package com.here2u.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 日期转换工具类
 * 
 * @author Joki
 * @version [V1.00, 2016年3月31日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class DateUtil
{
    /**
     * 格式：yyyy-MM-dd HH:mm
     */
    private final static String TIME_FORMAT = "yyyy-MM-dd HH:mm";
    
    /**
     * Date类型转String
     * 
     * 
     * @param date
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String format(Date date)
    {
        return date == null ? " " : DateFormatUtils.format(date, TIME_FORMAT);
    }
    
    /**
     * String类型转Date
     * 
     * 
     * @param date
     * @return
     * @throws ParseException
     * @see [类、类#方法、类#成员]
     */
    public static Date parse(String date)
        throws ParseException
    {
        return StringUtils.isBlank(date) ? null : DateUtils.parseDate(date, TIME_FORMAT);
    }

}
