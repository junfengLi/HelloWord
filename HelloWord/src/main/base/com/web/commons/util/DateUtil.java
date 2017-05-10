package com.web.commons.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtil
{
  public static String getFormatDate(long date)
  {
    if (date == 0L) {
      return "";
    }
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    return df.format(new Date(date));
  }

  public static String getFormatDateShort(long date)
  {
    if (date == 0L) {
      return "";
    }
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
    return df.format(new Date(date));
  }

  public static String getFormatDateWithoutDay(long date)
  {
    if (date == 0L) {
      return "";
    }
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
    return df.format(new Date(date));
  }

  public static String getFormatDateTime(long date)
  {
    if (date == 0L) {
      return "";
    }
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return df.format(new Date(date));
  }

  public static String getFormatDateTimeWithoutSecond(long date)
  {
    if (date == 0L) {
      return "";
    }
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    return df.format(new Date(date));
  }

  public static long getLongDateFromString(String dateString)
  {
    return getLongDateFromString(dateString, true);
  }

  public static long getLongDateFromString(String dateString, boolean hasDay) {
    SimpleDateFormat df = null;
    if (hasDay)
      df = new SimpleDateFormat("yyyy-MM-dd");
    else
      df = new SimpleDateFormat("yyyy-MM");
    try
    {
      if (StringUtils.isBlank(dateString))
        return 0L;
      return df.parse(dateString).getTime();
    } catch (ParseException e) {
      e.printStackTrace();
    }return 0L;
  }

  public static long getLongDateTimeFromString(String timeString)
  {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
      if (StringUtils.isBlank(timeString))
        return 0L;
      return df.parse(timeString).getTime();
    } catch (ParseException e) {
      e.printStackTrace();
    }return 0L;
  }

  public static long getLongDateMinuteFromString(String timeString)
  {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    try {
      if (StringUtils.isBlank(timeString))
        return 0L;
      return df.parse(timeString).getTime();
    } catch (ParseException e) {
      e.printStackTrace();
    }return 0L;
  }

  public static long getLongDateMilliTimeFromString(String timeString)
  {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    try {
      if (StringUtils.isBlank(timeString))
        return 0L;
      return df.parse(timeString).getTime();
    } catch (ParseException e) {
      e.printStackTrace();
    }return 0L;
  }

  public static String getFormatDateMilliTime(long time)
  {
    String strTime = String.valueOf(time);
    if (strTime.equals("0"))
      return "";
    String mill = strTime.substring(strTime.length() - 3);
    String dateTime = getFormatDateTime(time);

    return dateTime + ":" + mill;
  }

  public static String getFirstDayOfMonth(int year, int month)
  {
    Calendar cal = Calendar.getInstance();
    cal.set(1, year);
    cal.set(2, month - 1);
    cal.set(5, cal.getMinimum(5));
    return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
  }

  public static String getLastDayOfMonth(int year, int month)
  {
    Calendar cal = Calendar.getInstance();
    cal.set(1, year);
    cal.set(2, month - 1);
    cal.set(5, cal.getActualMaximum(5));
    return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
  }

  public static void main(String[] args)
  {
    long time = 1409500799999L;
    String start = getFormatDate(time) + " 00:00:00:000";
    String end = getFormatDate(time) + " 23:59:59:999";
    System.out.println(start);
    System.out.println(end);
    System.out.println(getLongDateMilliTimeFromString(start));
    System.out.println(getLongDateMilliTimeFromString(end));

    System.out.println(getFormatDateMilliTime(getLongDateMilliTimeFromString(start)));
    System.out.println(getFormatDateMilliTime(getLongDateMilliTimeFromString(end)));
  }
}