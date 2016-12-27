package com.cpfei.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by cpfei on 2016/12/27.
 * 处理时间的类
 */

public class TimeUtils {

    public static final String DATE_FMT1 = "yyyy-MM-dd";
    public static final String DATE_FMT2 = "yyyy.MM.dd";
    public static final String DATE_FMT3 = "yyyy/MM/dd";
    public static final String DATE_TIME_FMT1 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FMT2 = "yyyy:MM:dd HH:mm:ss";
    public static final String DATE_TIME_FMT2_SSS = "yyyy:MM:dd HH:mm:ss.SSS";
    public static final String DATE_TIME_FMT3 = "yyyy-MM-dd HH:mm";
    public static final String DATE_TIME_FMT4 = "yyyy.MM.dd  HH:mm";
    public static final String DATE_FMT1_HH_mm = "HH:mm";

    /**
     * Time constants
     */
    public static final long TIME_0_CLOCK = 0L;
    public static final long TIME_1_HOURS = 3600000L;
    public static final long TIME_1_DAY = 24 * 3600000L;
    public static final long TIME_1_MINS = 60000L;
    public static final long TIME_1_SECONDS = 1000L;


    /**
     * Convert string date time to long
     *
     * @param strDataTime
     *            The formated date time
     * @param strFormat
     *            Consts.DATE_TIME_FMT[1~4] or Consts.DATE_FMT[1~2]
     * @return long date time, -1 for failed
     */
    public static long getDateTime(String strDataTime, String strFormat) {
        if (strDataTime == null) {
            return -1;
        }

        ParsePosition pos = new ParsePosition(0);
        try {
            SimpleDateFormat sFormatter = new SimpleDateFormat(strFormat);
            sFormatter.setTimeZone(TimeZone.getDefault());

            Date datetime = sFormatter.parse(strDataTime, pos);
            if (datetime == null) {
                return -1;
            }
            return datetime.getTime();
        } catch (IllegalArgumentException ex) {
            return -1;
        }
    }


    /**
     * time Format
     * @param mills
     * @return
     */
    public static String getTimeFormat(long mills) {
        return getTimeFormat(mills, DATE_FMT1);
    }

    public static String getTimeFormat(long mills, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(new Date(mills));
    }

    /**
     * 得到两天之间 几晚
     * @param dateIn
     * @param dateOut
     * @return
     * @throws ParseException
     */
    public static int getBetweenDays(String dateIn, String dateOut,String dateFormat) {
        if (TextUtils.isEmpty(dateIn) || TextUtils.isEmpty(dateOut)) {
            return 0;
        }
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        long inRoom = 0;
        try {
            inRoom = df.parse(dateIn).getTime();
        } catch (ParseException e) {
        }
        long outRoom = 0;
        try {
            outRoom = df.parse(dateOut).getTime();
        } catch (ParseException e) {
        }
        return (int)((outRoom - inRoom) / TIME_1_DAY);
    }

    /**
     * 输入年月日
     * 得到星期几
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String getEE(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        int i = calendar.get(Calendar.DAY_OF_WEEK);

        switch (i - 1) {
            case 0:
                return "周日";
            case 1:
                return "周一";
            case 2:
                return "周二";
            case 3:
                return "周三";
            case 4:
                return "周四";
            case 5:
                return "周五";
            case 6:
                return "周六";
            default:
                return "";
        }
    }

    /**
     * Get Greenwich time by time zone
     *
     * @param time
     *            The local time, unit: ms
     * @param timeZone
     *            The time zone of local area, unit: ms
     * @return The Greenwich time
     */
    public static long getGreenwichTime(long time, long timeZone) {
        return time - timeZone;
    }

    /**
     * Get local time from Greenwich time by time zone
     *
     * @param greenwichTime
     *            The Greenwich time, unit: ms
     * @param timeZone
     *            The time zone of local area, unit: ms
     * @return Local time
     */
    public static long getLocalTime(long greenwichTime, long timeZone) {
        return greenwichTime + timeZone;
    }

    /**
     * Calculate the days across by date time in start time zone
     *
     * @param startDateTime
     *            The start date time in the first area, unit: ms
     * @param startTimeZone
     *            The time zone of the first area, unit: ms
     * @param endDateTime
     *            The end date time in the second area, unit: ms
     * @param endTimeZone
     *            The time zone of the second area, unit: ms
     * @return The days across
     */
    public static int getAcrossDaysByDateInStartTimeZone(long startDateTime,
                                                         long startTimeZone, long endDateTime, long endTimeZone) {
        long Ts = startDateTime;
        long Te = endDateTime + startTimeZone - endTimeZone;

        long ds = getDateMillis(Ts);
        int n = (int) ((Te - ds) / TIME_1_DAY);
        return n;
    }

    /**
     * Calculate the days across by date time int end time zone
     *
     * @param startDateTime
     *            The start date time in the first area, unit: ms
     * @param startTimeZone
     *            The time zone of the first area, unit: ms
     * @param endDateTime
     *            The end date time in the second area, unit: ms
     * @param endTimeZone
     *            The time zone of the second area, unit: ms
     * @return The days across
     */
    public static int getAcrossDaysByDateInEndTimeZone(long startDateTime,
                                                       long startTimeZone, long endDateTime, long endTimeZone) {
        long Ts = startDateTime - startTimeZone + endTimeZone;
        long Te = endDateTime;

        long ds = getDateMillis(Ts);
        int n = (int) ((Te - ds) / TIME_1_DAY);
        return n;
    }

    /**
     * Calculate the days across by start time and duration in start time zone
     *
     * @param startTime
     *            The start time in the first area, unit: ms
     * @param duration
     *            Duration between two areas, unit: ms
     * @return The days across
     */
    public static int getAcrossDaysInStartTimeZone(long startTime, long duration) {
        long Te = startTime + duration;
        int n = (int) (Te / TIME_1_DAY);
        return n;
    }

    /**
     * Calculate the days across by start time and duration in end time zone
     *
     * @param startTime
     * @param startTimeZone
     * @param endTimeZone
     * @param duration
     * @return The days across
     */
    public static int getAcrossDaysInEndTimeZone(long startTime,
                                                 long startTimeZone, long endTimeZone, long duration) {
        long Te = startTime - startTimeZone + endTimeZone + duration;

        int n = (int) (Te / TIME_1_DAY);
        return n;
    }

    /**
     * Check the time is across day between 24 hours
     *
     * @param startTime
     * @param startTimeZone
     * @param endTime
     * @param endTimeZone
     * @return
     */
    public static boolean isAcrossDayIn24h(long startTime, long startTimeZone,
                                           long endTime, long endTimeZone) {
        long Ts = startTime % TIME_1_DAY;
        long Te = (endTime + startTimeZone - endTimeZone) % TIME_1_DAY;

        if (Te <= Ts) {
            return true;
        }

        return false;
    }

    /**
     * Rectify the across days between 24 hours
     *
     * @param startTime
     * @param startTimeZone
     * @param endTime
     * @param endTimeZone
     * @param acrossDays
     * @return
     */
    public static int rectifyAcrossDaysIn24h(long startTime,
                                             long startTimeZone, long endTime, long endTimeZone, int acrossDays) {
        if (isAcrossDayIn24h(startTime, startTimeZone, endTime, endTimeZone)) {
            if (acrossDays == 0) {
                return 1;
            }
        }

        return acrossDays;
    }

    /**
     * Get delta time between two areas
     *
     * @param startDateTime
     *            The start date time in the first area, unit: ms
     * @param startTimeZone
     *            The time zone of the first area, unit: ms
     * @param endDateTime
     *            The end date time in the second area, unit: ms
     * @param endTimeZone
     *            The time zone of the second area, unit: ms
     * @return The really delta time
     */
    public static long getDeltaDateTime(long startDateTime, long startTimeZone,
                                        long endDateTime, long endTimeZone) {
        long Ts = getGreenwichTime(startDateTime, startTimeZone);
        long Te = getGreenwichTime(endDateTime, endTimeZone);
        return Te - Ts;
    }

    /**
     * Get delta time between two areas
     *
     * @param startTime
     *            The start time in the first area, unit: ms
     * @param startTimeZone
     *            The time zone of the first area, unit: ms
     * @param endTime
     *            The end time in the second area, unit: ms
     * @param endTimeZone
     *            The time zone of the second area, unit: ms
     * @return The really delta time
     */
    @Deprecated
    public static long getDeltaTime(long startTime, long startTimeZone,
                                    long endTime, long endTimeZone) {
        long Ts = getGreenwichTime(startTime, startTimeZone);
        long Te = getGreenwichTime(endTime, endTimeZone);
        if (Te <= Ts) {
            Te += TIME_1_DAY;
        }
        return Math.abs(Te - Ts);
    }

    /**
     * Get delta time between two areas
     *
     * @param startTime
     *            The start time in the first area, unit: ms
     * @param startTimeZone
     *            The time zone of the first area, unit: ms
     * @param endTime
     *            The end time in the second area, unit: ms
     * @param endTimeZone
     *            The time zone of the second area, unit: ms
     * @param acrossDays
     *            Days across
     * @return The really delta time
     */
    public static long getDeltaTime(long startTime, long startTimeZone,
                                    long endTime, long endTimeZone, int acrossDays) {
        long Ts = startTime % TIME_1_DAY;
        long Te = (endTime + startTimeZone - endTimeZone) % TIME_1_DAY;

        long delta = Te + acrossDays * TIME_1_DAY - Ts;
        return delta;
    }

    /**
     * Get the date from date time.
     *
     * @param dateTime
     *            The milliseconds of date time
     * @param timeZone
     *            The time zone of the date time
     * @param localTimeZone
     *            The specified time zone to get date
     * @return The date of the date time. The time zone of date is the same as
     *         the time zone of date time
     */
    public static long getDateMillis(long dateTime, long timeZone,
                                     long localTimeZone) {
        long localDateTime = dateTime - timeZone + localTimeZone;
        long localDate = getDateMillis(localDateTime);
        return localDate - localTimeZone + timeZone;
    }

    /**
     * Get the date from date time. The date and date time are in the same time
     * zone
     *
     * @param dateTime
     *            The milliseconds of date time
     * @return The date of the date time. The time zone of date is the same as
     *         the time zone of date time
     */
    public static long getDateMillis(long dateTime) {
        long date = dateTime / TIME_1_DAY;
        return date * TIME_1_DAY;
    }

    /**
     * Get the clock from date time. The clock and date time are in the same
     * time zone
     *
     * @param dateTime
     *            The milliseconds of date time
     * @return The clock of the date time. The time zone of clock is the same as
     *         the time zone of date time
     */
    public static long getClockMillis(long dateTime) {
        long date = getDateMillis(dateTime);
        return dateTime - date;
    }

    /**
     * Get the hours from given time. The hours and the time are in the same
     * time zone
     *
     * @param time
     *            The milliseconds of time
     * @return The hours of the time. The time zone of the hours is the same as
     *         the time zone of given time
     */
    public static long getHour(long time) {
        long hour = time / 3600000L;
        return hour % 24;
    }

    /**
     * Get the minutes from given time. The minutes and the time are in the same
     * time zone
     *
     * @param time
     *            The milliseconds of time
     * @return The minutes of the time. The time zone of the minutes is the same
     *         as the time zone of given time
     */
    public static long getMinute(long time) {
        long minute = time / 60000L;
        return minute % 60;
    }

    /**
     * Get the seconds from given time. The seconds and the time are in the same
     * time zone
     *
     * @param time
     *            The milliseconds of time
     * @return The seconds of the time. The time zone of the seconds is the same
     *         as the time zone of given time
     */
    public static long getSecond(long time) {
        long second = time / 1000L;
        return second % 60;
    }

    /**
     * Get the milliseconds from given time. The milliseconds and the time are
     * in the same time zone
     *
     * @param time
     *            The milliseconds of time
     * @return The milliseconds of the time. The time zone of milliseconds is
     *         the same as the time zone of given time
     */
    public static long getMillisecond(long time) {
        return time % 1000L;
    }

    /**
     * Get Greenwich time from formated date time which is in local time zone
     *
     * @param date
     *            Local date time
     * @param format
     * @return The Greenwich time
     */
    public static long parseDate(String date, String format) {
        long l = 0l;
        try {
            SimpleDateFormat ft = new SimpleDateFormat(format);
            Date dateString = ft.parse(date);
            l = dateString.getTime();
        } catch (Exception e) {
            return l;
        }

        return l;
    }

    /**
     * Get Greenwich time from formated date time which is in specified time
     * zone
     *
     * @param date
     *            Date time in specified zone
     * @param format
     * @param timeZone
     *            Specified time zone
     * @return The Greenwich time
     */
    public static long parseDate(String date, String format, long timeZone) {
        long l = 0l;
        try {
            SimpleDateFormat ft = new SimpleDateFormat(format);

            String[] zoneIds = TimeZone.getAvailableIDs((int) timeZone);
            if (zoneIds != null && zoneIds.length > 0) {
                TimeZone zone = TimeZone.getTimeZone(zoneIds[0]);
                ft.setTimeZone(zone);
            }

            Date dateString = ft.parse(date);
            l = dateString.getTime();
        } catch (Exception e) {
            return l;
        }

        return l;
    }

    /**
     * Get formated date time in local time zone from Greenwich time
     *
     * @param date
     *            Greenwich time
     * @param format
     * @return Local formated date time
     */
    public static String formatDate(long date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Date dt = new Date(date);
        String b = df.format(dt);

        return b;
    }

    /**
     * Get formated date time in specified time zone from Greenwich time
     *
     * @param date
     *            Greenwich time
     * @param format
     * @param timeZone
     *            Specified time zone
     * @return Formated date time in zone
     */
    public static String formatDate(long date, String format, long timeZone) {
        SimpleDateFormat df = new SimpleDateFormat(format);

        String[] zoneIds = TimeZone.getAvailableIDs((int) timeZone);
        if (zoneIds != null && zoneIds.length > 0) {
            TimeZone zone = TimeZone.getTimeZone(zoneIds[0]);
            df.setTimeZone(zone);
        }

        Date dt = new Date(date);
        String b = df.format(dt);

        return b;
    }

    /**
     * Get the time after the specified date
     *
     * @param date
     *            The specified base date
     * @param days
     *            The days to add to base date
     * @param hours
     *            The hours to add to base date
     * @param minutes
     *            The minutes to add to base date
     * @param seconds
     *            The seconds to add to base date
     * @param mills
     *            The milliseconds to add to base date
     * @return The expected time after the specified date
     */
    public static long getDateAfter(Date date, int days, int hours,
                                    int minutes, int seconds, int mills) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + days);

        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, seconds);
        calendar.set(Calendar.MILLISECOND, mills);

        return calendar.getTimeInMillis();
    }

    /**
     * Get the time before the specified date
     *
     * @param date
     *            The specified base date
     * @param days
     *            The days to subtract from base date
     * @param hours
     *            The hours to add to base date
     * @param minutes
     *            The minutes to add to base date
     * @param seconds
     *            The seconds to add to base date
     * @param mills
     *            The milliseconds to add to base date
     * @return The expected time before the specified date
     */
    public static long getDateBefore(Date date, int days, int hours,
                                     int minutes, int seconds, int mills) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - days);

        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, seconds);
        calendar.set(Calendar.MILLISECOND, mills);

        return calendar.getTimeInMillis();
    }

    /**
     * Get the time after the specified date
     *
     * @param date
     *            The specified base date
     * @param days
     *            The days to add to base date
     * @param hours
     *            The hours to add to base date
     * @param minutes
     *            The minutes to add to base date
     * @param seconds
     *            The seconds to add to base date
     * @param mills
     *            The milliseconds to add to base date
     * @return The expected time after the specified date
     */
    public static long getDateAfter(long date, int days, int hours,
                                    int minutes, int seconds, int mills) {
        return date + TIME_1_DAY * days + TIME_1_HOURS * hours + TIME_1_MINS
                * minutes + TIME_1_SECONDS * seconds + mills;
    }

    /**
     * Get the time before the specified date
     *
     * @param date
     *            The specified base date
     * @param days
     *            The days to subtract from base date
     * @param hours
     *            The hours to add to base date
     * @param minutes
     *            The minutes to add to base date
     * @param seconds
     *            The seconds to add to base date
     * @param mills
     *            The milliseconds to add to base date
     * @return The expected time before the specified date
     */
    public static long getDateBefore(long date, int days, int hours,
                                     int minutes, int seconds, int mills) {
        return date - TIME_1_DAY * days + TIME_1_HOURS * hours + TIME_1_MINS
                * minutes + TIME_1_SECONDS * seconds + mills;
    }

    private static void testDecomposeTime() {
        long time = System.currentTimeMillis();
        System.out.println("time(ms): " + time);

        String format = "yyyy-MM-dd HH:mm:ss";
        String strTime = formatDate(time, format);
        System.out.println("raw time: " + strTime);

        long date = getDateMillis(time);
        long hour = getHour(time);
        long minute = getMinute(time);
        long second = getSecond(time);
        long millis = getMillisecond(time);
        System.out.println("decomposed time: " + date + ", " + hour + ":"
                + minute + ":" + second + "'" + millis);

        long rebuildTime = date + hour * 3600000L + minute * 60000L + second
                * 1000L + millis;
        System.out.println("rebuild time(ms): " + rebuildTime);

        String strDate = formatDate(rebuildTime, format);
        System.out.println("rebuild time: " + strDate);

        System.out.println("error: " + (rebuildTime - time));
    }

    private static void testAcrossDay() {
        String format = "yyyy-MM-dd HH:mm:ss";

        // Simulate time for China
        String strDate1 = "2016-03-20 22:12:50";
        long greenwichTime1 = parseDate(strDate1, format); // UTC
        long timeZone1 = 8 * 3600000L;
        long dateTime1 = getLocalTime(greenwichTime1, timeZone1); // UTC+8

        // Simulate time for east America
        String strDate2 = "2016-03-21 00:01:30";
        long greenwichTime2 = parseDate(strDate2, format); // UTC
        long timeZone2 = -5 * 3600000L;
        long dateTime2 = getLocalTime(greenwichTime2, timeZone2); // UTC-5

        // Test path 1
        int days = getAcrossDaysByDateInStartTimeZone(dateTime1, timeZone1,
                dateTime2, timeZone2);
        System.out.println("across days from date time: " + days);

        long time1 = getClockMillis(dateTime1);
        long time2 = getClockMillis(dateTime2);
        long delta = getDeltaTime(time1, timeZone1, time2, timeZone2, days);
        System.out.println("delta from across day: " + delta);

        // Test path 2
        long delta2 = getDeltaDateTime(dateTime1, timeZone1, dateTime2,
                timeZone2);
        System.out.println("delta from date time: " + delta2);

        int days2 = getAcrossDaysInStartTimeZone(time1, delta2);
        System.out.println("across days from delta: " + days2);
    }

    private static void testParseDate() {
        String format = "yyyy-MM-dd HH:mm:ss";

        String strDate = "2016-03-20 22:12:50";
        System.out.println(strDate);

        long timeZone1 = 8 * 3600000L;
        long timeZone2 = -5 * 3600000L;
        long greenwichTime = parseDate(strDate, format, timeZone1);

        String strDate1 = formatDate(greenwichTime, format, timeZone1);
        System.out.println(strDate1);

        String strDate2 = formatDate(greenwichTime, format, timeZone2);
        System.out.println(strDate2);
    }

    public static void main(String[] args) {
        System.out.println("---------test decompose time----------");
        testDecomposeTime();
        System.out.println("-------------------");

        System.out.println("---------test across day----------");
        testAcrossDay();
        System.out.println("-------------------");

        System.out.println("---------test parse date----------");
        testParseDate();
        System.out.println("-------------------");
    }



}
