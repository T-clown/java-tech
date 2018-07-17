package java8.feature;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import org.junit.Test;

public class FeatureDate {

    @Test
    public void date2() {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.now();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        Date date = Date.from(zdt.toInstant());
        System.out.println(date.toString());
    }

    /**
     * Date 转 LocalDateTime
     */
    @Test
    public void dateToLocalDateTime() {
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        System.out.println(localDateTime);
        System.out.println(LocalDateTime.now());
    }

    /**
     * Date 转 LocalDate
     */
    @Test
    public void dateToLocalTime() {
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        System.out.println(localDate);
        System.out.println(LocalDate.now());
        System.out.println(localDateTime.toLocalTime());
    }

    /**
     * LocalDateTime --> Date
     */
    @Test
    public void localDateTimeToDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        System.out.println(date);
        System.out.println(new Date());
    }

    /**
     * LocalDate --> Date
     */
    @Test
    public void localDateToUdate() {
        LocalDate localDate = LocalDate.now();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        System.out.println(date);
        System.out.println(new Date());
    }

    /**
     * LocalTime --> Date
     */
    @Test
    public void localTimeToUdate() {
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        System.out.println(date);
        System.out.println(new Date());
    }

    @Test
    public void localDate(){
        // 取当前日期：
        LocalDate today = LocalDate.now(); // -> 2014-12-24
        System.out.println(today);
        // 根据年月日取日期，12月就是12：
        LocalDate crischristmas = LocalDate.of(2018, 12, 1); // -> 2014-12-25
        System.out.println(LocalDateTime.of(2018,6,1,0,0,0));
        System.out.println(crischristmas);
        // 根据字符串取：
        LocalDate endOfFeb = LocalDate.parse("2018-08-28"); // 严格按照ISO yyyy-MM-dd验证，02写成2都不行，当然也有一个重载方法允许自己定义格式
        System.out.println(endOfFeb);
        //LocalDate.parse("2014-02-29"); // 无效日期无法通过：DateTimeParseException: Invalid date

        // 取本月第1天：
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth()); // 2014-12-01
        System.out.println(firstDayOfThisMonth);
        System.out.println("firstDay"+LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()));
        // 取本月第2天：
        LocalDate secondDayOfThisMonth = today.withDayOfMonth(2); // 2014-12-02
        System.out.println(secondDayOfThisMonth);
        // 取本月最后一天，再也不用计算是28，29，30还是31：
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth()); // 2014-12-31
        System.out.println(lastDayOfThisMonth);
        // 取下一天：
        LocalDate firstDayOf2015 = lastDayOfThisMonth.plusDays(1); // 变成了2015-01-01
        System.out.println(firstDayOf2015);
        // 取2015年1月第一个周一，这个计算用Calendar要死掉很多脑细胞：
        LocalDate firstMondayOf2015 = LocalDate.parse("2015-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); // 2015-01-05
        System.out.println(firstMondayOf2015);

    }

    @Test
    public void localDateTest(){
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now().getYear(),LocalDate.now().getMonth(),1,0,0,0);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        System.out.println(date);
    }

}
