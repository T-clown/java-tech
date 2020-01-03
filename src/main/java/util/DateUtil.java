package util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.lang3.time.DateFormatUtils;

public class DateUtil {
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式化日期工具
     */
    public static final FastDateFormat FAST_DATE_FORMAT = FastDateFormat.getInstance(YYYY_MM_DD_HH_MM_SS);


    private static volatile Map<String, DateTimeFormatter> formatterMap = new ConcurrentHashMap<>();
    private static volatile Map<String, SimpleDateFormat> simpleDateFormatMap = new ConcurrentHashMap<>();

    private static DateTimeFormatter formatter(String pattern) {
        DateTimeFormatter formatter = formatterMap.get(pattern);
        if (formatter == null) {
            formatterMap.put(pattern, formatter = DateTimeFormatter.ofPattern(pattern));
        }
        return formatter;
    }

    private static SimpleDateFormat simpleDateFormat(String pattern) {
        SimpleDateFormat formatter = simpleDateFormatMap.get(pattern);
        if (formatter == null) {
            simpleDateFormatMap.put(pattern, formatter = new SimpleDateFormat(pattern));
        }
        return formatter;
    }

    /**
     * 当月第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMon(Date date) {
        LocalDateTime firstOfDay = transToLocateDateTime(date).with(TemporalAdjusters.firstDayOfMonth());
        return transToDate(firstOfDay);
    }

    /**
     * 当月最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMon(Date date) {
        LocalDateTime endOfDay = transToLocateDateTime(date).with(TemporalAdjusters.lastDayOfMonth()).with(
            LocalTime.MAX);
        return transToDate(endOfDay);
    }

    /**
     * LocalDateTime转Date
     *
     * @param localDateTime
     * @return
     */
    private static Date transToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date转LocalDateTime
     *
     * @param date
     * @return
     */
    private static LocalDateTime transToLocateDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 获得某天最大时间 yyyy-MM-dd 23:59:59
     *
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()),
            ZoneId.systemDefault());
        ;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获得某天最小时间 yyyy-MM-dd 00:00:00
     *
     * @param date
     * @return
     */
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()),
            ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        Date date = new Date();
        System.out.println(FAST_DATE_FORMAT.format(date));
        System.out.println(formatter(YYYY_MM_DD_HH_MM_SS).format(dateTime));
        System.out.println(DateFormatUtils.format(date, YYYY_MM_DD_HH_MM_SS));
    }

}