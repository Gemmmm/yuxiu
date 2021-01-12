package com.tc.yuxiu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    private static Date date = null;
    private static Integer todayOfMonth = null;
    private static Calendar calendar = null;

    public static long sevenDaysAgo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = new Date();// 取时间
        calendar = new GregorianCalendar();
        todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        long today = calendar.getTime().getTime();
        //System.out.println("现在：" + sdf.format(today));
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, todayOfMonth - 7);
        long recentSevenDay = calendar.getTime().getTime();
        //System.out.println("7天前：" + sdf.format(recentSevenDay));
        return recentSevenDay;

    }

    public static long fifteenDaysAgo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = new Date();// 取时间
        calendar = new GregorianCalendar();
        todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        long today = calendar.getTime().getTime();
        //System.out.println("现在：" + sdf.format(today));
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, todayOfMonth - 15);
        long recentSevenDay = calendar.getTime().getTime();
        //System.out.println("15天前：" + sdf.format(recentSevenDay));
        return recentSevenDay;

    }

    public static long thirtyDaysAgo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = new Date();// 取时间
        calendar = new GregorianCalendar();
        todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        long today = calendar.getTime().getTime();
        //System.out.println("现在：" + sdf.format(today));
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, todayOfMonth - 30);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long thirtyDayao = calendar.getTime().getTime();
        //System.out.println("30天前：" + sdf.format(thirtyDayao));
        return thirtyDayao;

    }

    public static long thisDayBegin() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = new Date();// 取时间
        calendar = new GregorianCalendar();
        todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        long today = calendar.getTime().getTime();
        //System.out.println("现在：" + sdf.format(today));
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long recentSevenDay = calendar.getTime().getTime();
        //System.out.println("今天开始：" + sdf.format(recentSevenDay));
        return recentSevenDay;
    }

    public static long lastdayBegin() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = new Date();// 取时间
        calendar = new GregorianCalendar();
        todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        long today = calendar.getTime().getTime();
        //System.out.println("现在：" + sdf.format(today));
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date time = calendar.getTime();
        //System.out.println("昨天开始：" + sdf.format(time));
        return time.getTime();
    }

    public static long thisMonthBegin() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = new Date();// 取时间
        calendar = new GregorianCalendar();
        todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        long today = calendar.getTime().getTime();
        //System.out.println("现在：" + sdf.format(today));
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        long thisMonthBegin = calendar.getTime().getTime();
        //System.out.println("这个月开始：" + sdf.format(thisMonthBegin));
        return thisMonthBegin;
    }

    public static long thisWeekBegin() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = new Date();// 取时间
        calendar = new GregorianCalendar();
        todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        long todayLong = calendar.getTime().getTime();
        //System.out.println("现在：" + sdf.format(todayLong));

        // 一周第一天是否为星期天
        boolean isFirstSunday = (calendar.getFirstDayOfWeek() == Calendar.SUNDAY);
        // 获取周几
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        // 若一周第一天为星期天，则-1
        if (isFirstSunday) {
            weekDay = weekDay - 1;
            if (weekDay == 0) {
                weekDay = 7;
            }
        }
        int today = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, today - weekDay + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long time = calendar.getTime().getTime();
        //System.out.println("这周开始：" + sdf.format(time));
        return time;

    }

    public static long lastWeekBegin() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = new Date();// 取时间
        calendar = new GregorianCalendar();
        todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        long todayLong = calendar.getTime().getTime();
        //System.out.println("现在：" + sdf.format(todayLong));

        // 一周第一天是否为星期天
        boolean isFirstSunday = (calendar.getFirstDayOfWeek() == Calendar.SUNDAY);
        // 获取周几
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        // 若一周第一天为星期天，则-1
        if (isFirstSunday) {
            weekDay = weekDay - 1;
            if (weekDay == 0) {
                weekDay = 7;
            }
        }
        int today = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, today - weekDay - 6);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long time = calendar.getTime().getTime();
        //System.out.println("上周开始：" + sdf.format(time));
        return time;

    }

    public static String theDayOfWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = new Date();// 取时间
        calendar = new GregorianCalendar();
        todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        long today = calendar.getTime().getTime();
        //System.out.println("现在：" + sdf.format(today));
        calendar.setTime(date);
        // 一周第一天是否为星期天
        boolean isFirstSunday = (calendar.getFirstDayOfWeek() == Calendar.SUNDAY);
        // 获取周几
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        // 若一周第一天为星期天，则-1
        if (isFirstSunday) {
            weekDay = weekDay - 1;
            if (weekDay == 0) {
                weekDay = 7;
            }
        }
        String whatDay = "";
        switch (weekDay) {
            case 1:
                whatDay = "星期一";
                break;
            case 2:
                whatDay = "星期二";
                break;
            case 3:
                whatDay = "星期三";
                break;
            case 4:
                whatDay = "星期四";
                break;
            case 5:
                whatDay = "星期五";
                break;
            case 6:
                whatDay = "星期六";
                break;
            case 7:
                whatDay = "星期日";
                break;
            default:
                break;
        }
        return whatDay;

    }


    public static String thisDayBeginNew() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM-dd");
        date = new Date();
        calendar = new GregorianCalendar();
        todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        long todayLong = calendar.getTime().getTime();
        //System.out.println("现在：" + sdf.format(todayLong));
        date = new Date();

        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long thisDayBegin = calendar.getTime().getTime();
        //System.out.println("今天开始：" + sdf.format(thisDayBegin));
        return sdf.format(thisDayBegin);
    }



    public static void main(String[] args) {
        String s = thisDayBeginNew();
        System.out.println(s);


    }


    public static String getNow() {
        Long time = System.currentTimeMillis();
        return time.toString();
    }
}
