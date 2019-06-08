package utils;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    private static final String DATE_FORMAT_PATTEN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式化日期工具
     */
    public static final FastDateFormat FAST_DATE_FORMAT = FastDateFormat.getInstance(DATE_FORMAT_PATTEN);

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTEN );


    /**
     * 获得某天最大时间 2018-11-22 23:59:59
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获得某天最小时间 2018-11-22 00:00:00
     * @param date
     * @return
     */
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String[] args) {
        LocalDateTime dateTime=LocalDateTime.now();
        Date date=new Date();
        System.out.println(FAST_DATE_FORMAT.format(date));
        System.out.println(DATE_TIME_FORMATTER.format(dateTime));
        System.out.println(DateFormatUtils.format(date,DATE_FORMAT_PATTEN));
    }

}