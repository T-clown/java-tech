package utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;

public class DateUtil {
    private static final String DATE_FORMAT_PATTEN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式化日期工具
     */
    public static final FastDateFormat DATE_FORMAT_TOOL = FastDateFormat.getInstance(DATE_FORMAT_PATTEN);


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

}
