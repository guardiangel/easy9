package org.colin.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Description: 日期时间工具类
 * @Author: wujiangbo(QQ:1134135987)
 * @Date: 2020/09/10 13:33:19
 */
public class DateUtils {

    public static String YYYY = "yyyy";
    public static String YYYYMM = "yyyyMM";
    public static String YYYY_MM = "yyyy-MM";
    public static String YYYY_MM_DD = "yyyy-MM-dd";
    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) throws Exception {
//        LocalDateTime localDateTime = LocalDateTime.parse("2020-09-10 10:57:00", pattern_yyyy_MM_DD_HH_mm_ss);
//        Date d1 = localDateTime2Date(localDateTime);
//        System.out.println(getDatePoor(new Date(), d1));

//        Long s1 = System.currentTimeMillis();
//        Thread.sleep(5000);
//        Long s2 = System.currentTimeMillis();
//        System.out.println(formatMillis2DDHHMM(s2-s1));

//        System.out.println(getCurrentTimeByPattern(YYYY_MM_DD_HH_MM_SS));

        System.out.println(stringTime2Date("2020-01-12 12:12:12", YYYY_MM_DD_HH_MM_SS));
    }

    //将输入的毫秒数转换成xx天xx小时xx分钟的形式
    public static String formatMillis2DDHHMM(Long milllis){
        StringBuilder sb = new StringBuilder();
        int dayNum = milllis.intValue() / (1000 * 60 * 60 * 24);
        int hourNum = (milllis.intValue() % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        int minNum = (milllis.intValue() - dayNum * 1000 * 60 * 60 * 24 - hourNum * 1000 * 60 * 60) / (1000 * 60);
        int secondNum = (milllis.intValue() - dayNum * 1000 * 60 * 60 * 24 - hourNum * 1000 * 60 * 60 - minNum * 1000 * 60) / 1000;
        sb.append(formatDate(dayNum));
        sb.append("天");
        sb.append(formatDate(hourNum));
        sb.append("小时");
        sb.append(formatDate(minNum));
        sb.append("分钟");
        sb.append(formatDate(secondNum));
        sb.append("秒");
        return sb.toString();
    }

    //对时间数字进行补0操作，0-9需要在前面补0
    private static String formatDate(int dateNum){
        String dateStr = "" + dateNum;
        if(dateNum >= 0 && dateNum < 10){
            dateStr = "0" + dateNum;
        }
        return dateStr;
    }

    //计算两个时间相差多久
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒
        long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
    }

    /**
     * @description: 按照指定格式获取当前时间格式串
     * @author: wujiangbo(QQ:1134135987)
     * @date: 2020-09-15 11:38:40
     * @param: pattern 日期时间格式串
     * @return: java.lang.String
     */
    public static String getCurrentTimeByPattern(String pattern) {
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime date = LocalDateTime.now(zone);
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * @Description: 获取当前时间串(格式：yyyy-MM-dd HH:mm:ss)
     * @Author: wujiangbo(QQ:1134135987)
     * @Date: 2020/9/26 21:24
     */
    public static String getCurrentTimeByPattern() {
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime date = LocalDateTime.now(zone);
        return date.format(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));
    }

    /**
     * @description: 按照指定格式将字符串转成Date类型
     * @author: wujiangbo(QQ:1134135987)
     * @date: 2020-09-15 11:39:17
     * @param: strDate
     * @param: pattern
     * @return: java.util.Date
     */
    public static Date stringTime2Date(String strDate, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern(pattern));
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }
}
