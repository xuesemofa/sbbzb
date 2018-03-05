package org.watch.com.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Dates2 {
    /**
     * 返回当前时间字符串
     *
     * @return String
     */
    public static String getDateTimeString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 采用默认的格式
        return sdf.format(new Date());

    }

    /**
     * 将字符串转成时间
     *
     * @param DateString
     * @return
     */
    public static Date getDate(String DateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 采用默认的格式
        try {
            return sdf.parse(DateString);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回日期转换成字符串yyyyMMdd
     *
     * @param date --日期
     * @return String
     */
    public static String getDateString1(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 采用年月日
        return sdf.format(date);
    }

    /**
     * 精确到微秒
     *
     * @param DateString
     * @return
     */
    public static Date getDate1(String DateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // 采用默认的格式
        try {
            return sdf.parse(DateString);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回系统日期时间字符串
     *
     * @param datetimeType --时间格式(如yyyyMMddHHmmss)
     * @return String
     */
    public static String getDateTimeString(String datetimeType) {
        SimpleDateFormat sdf = new SimpleDateFormat(datetimeType);
        return sdf.format(new Date());
    }

    /**
     * 返回系统时间字符串
     *
     * @param dateTime --日期时间
     * @return String
     */
    public static String getDateTimeString(Date dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 采用默认的格式
        return sdf.format(dateTime);
    }

    /**
     * 返回系统时间字符串精确到微秒
     *
     * @param dateTime --日期时间
     * @return String
     */
    public static String getDateTimeString1(Date dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // 采用默认的格式
        return sdf.format(dateTime);
    }

    /**
     * 返回系统日期字符串
     *
     * @param date --日期
     * @return String
     */
    public static String getDateString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 采用默认的格式
        return sdf.format(date);
    }

    /**
     * 返回系统时间字符串
     *
     * @param date         --时间
     * @param datetimeType --时间格式(如yyyyMMddHHmmss)
     * @return String
     */
    public static String getDateTimeString(Date date, String datetimeType) {
        SimpleDateFormat sdf = new SimpleDateFormat(datetimeType);
        return sdf.format(date);
    }

    /**
     * 一个日期加上小时后变成新的时间
     *
     * @param date   日期
     * @param minute 小时
     * @return 返回相加后的日期
     */
    public static java.util.Date addDate(Date date, long minute) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + minute * 60 * 1000);
        return c.getTime();
    }

    /**
     * 一个日期减去分钟后变成新的时间
     *
     * @param date 日期
     *             分种
     * @return 返回相加后的日期
     */
    public static java.util.Date subDate(Date date, long minute) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) - minute * 60 * 1000);
        return c.getTime();
    }

    /**
     * 一个日期减去分钟后变成新的时间
     *
     * @param date 日期
     *             分种
     * @return 返回相加后的日期
     */
    public static java.util.Date subDate1(Date date, int second) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) - second * 1000);
        return c.getTime();
    }

    /**
     * 返回毫秒
     *
     * @param date 日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 两个日期之间相差几秒
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static int subDate(String beginTime, String endTime) {
        Date begin = getDate(beginTime);
        Date end = getDate(endTime);
        return (int) (getMillis(end) - getMillis(begin) / 1000);
    }

    /**
     * 将时间格式的字符串转换成时间类型
     *
     * @param datetimeString --时间格式的字符串
     * @return
     */
    public static Date parseStringToDate(String datetimeString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 采用默认的格式
        Date dt = null;
        try {
            dt = sdf.parse(datetimeString);
        } catch (Exception e) {
        }
        return dt;
    }

    /**
     * 将时间格式的字符串转换成时间类型
     *
     * @param datetimeString --时间格式的字符串
     * @param datetimeType   --时间格式
     * @return
     */
    public static Date parseStringToDate(String datetimeString,
                                         String datetimeType) {
        SimpleDateFormat sdf = new SimpleDateFormat(datetimeType);
        Date dt = null;
        try {
            dt = sdf.parse(datetimeString);
        } catch (Exception e) {
        }
        return dt;
    }

    /**
     * 获得某一日期的前一天
     *
     * @param date
     * @return Date
     */
    public static Date getPreviousDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - 1);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 日期转星期
     *
     * @param datetime
     * @return
     */
    public static int dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return w;
    }

    /**
     * 获取某段时这里写代码片间内的所有日期
     *
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static List<Date> findDates(Date dBegin, Date dEnd) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }
}
