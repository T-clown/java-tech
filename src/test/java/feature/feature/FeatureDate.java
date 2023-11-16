package feature.feature;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import redis.clients.jedis.Response;
import util.DateTImeUtil;
import util.DateUtil;

public class FeatureDate {

    @Data
    static class Obj<T> {
        private List<T> items;
    }

    @Data
    static class Item {
        private Integer id;

        private String name;

        private List<Integer> industry;
    }

    /**
     * 反序列化泛型类
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> Obj<T> parse(String json, Class<T> clazz) {
       return JSON.parseObject(json, new TypeReference<Obj<T>>(clazz) {});
    }
    public <T> Obj<T> parse2(String json, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        JavaType type = mapper.getTypeFactory().constructParametricType(Obj.class, clazz);
        Obj<T> deserializedObj = null;
        try {
            deserializedObj = mapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return deserializedObj;
    }

    @Test
    public void date2() {

        String a = "{\"items\":[{\"id\":1,\"name\":\"client\",\"industry\":[45,58]},{\"id\":2,\"name\":\"test\",\"industry\":[\"56\"]},{\"id\":3,\"name\":\"台积电\",\"industry\":[]},{\"id\":5,\"name\":\"test1\",\"industry\":[\"60\",\"58\"]},{\"id\":6,\"name\":\"李宁\",\"industry\":[\"44\"]}]}";
        // 反序列化泛型类
        Obj<Item> parse = parse(a, Item.class);
        parse.getItems().forEach(x -> System.out.println(x.id));
        System.out.println();
        // 反序列化泛型类
        Obj<Item> parse2 = parse2(a, Item.class);
        parse2.getItems().forEach(x -> System.out.println(x.id));

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
    public void localDate() {
        // 取当前日期：
        LocalDate today = LocalDate.now(); // -> 2014-12-24
        System.out.println(today);
        // 根据年月日取日期，12月就是12：
        LocalDate crischristmas = LocalDate.of(2018, 12, 1); // -> 2014-12-25
        System.out.println(LocalDateTime.of(2018, 6, 1, 0, 0, 0));
        System.out.println(crischristmas);
        // 根据字符串取：
        LocalDate endOfFeb = LocalDate.parse("2018-08-28"); // 严格按照ISO yyyy-MM-dd验证，02写成2都不行，当然也有一个重载方法允许自己定义格式
        System.out.println(endOfFeb);
        //LocalDate.parse("2014-02-29"); // 无效日期无法通过：DateTimeParseException: Invalid date

        // 取本月第1天：
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth()); // 2014-12-01
        System.out.println(firstDayOfThisMonth);
        System.out.println("firstDay" + LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()));
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


}
