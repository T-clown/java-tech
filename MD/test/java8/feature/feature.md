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
aaaaaaaaaaaaaaaaaaaaaaaaaaaaa
package java8.feature;

import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

import java8.entity.Student;
import java8.utils.DateComparator;
import org.junit.Before;
import org.junit.Test;
import static java.util.stream.Collectors.groupingBy;

public class FeatureOne {
    private List<Student> list = new ArrayList<>();

    @Before
    public void setup() {
        list.add(new Student(1, "", "", 1));
        list.add(new Student(2, "", "", 2));
        list.add(new Student(3, "", "", -1));
        list.add(new Student(2, "", "", 2));
        list.add(new Student(1, "", "", 4));
        list.add(new Student(1, "", "", 7));
    }

    @Test
    public void demo01() {
        //Arrays.asList("a","b","d").forEach(e->System.out.println(e));
        Arrays.asList("a", 'b', "c").forEach(x -> {
            System.out.println(x);
        });
        //Arrays.asList("a",'b',"c").forEach(System.out::println);
        String separator = ",";
        //Arrays.asList("a", "b", "dddd").forEach((String e) -> System.out.print(e + separator));

    }

    @Test
    public void demo() {
        Map<Integer, Long> map2 = list.stream().collect(
            groupingBy(Student::getId, Collectors.summingLong(Student::getScore)));
        System.out.println(map2.get(1));

        DateComparator d = new DateComparator();
        System.out.println("最大值：" + Collections.max(list, d).score);
        System.out.println("最小值：" + Collections.min(list, d).score);
    }

    @Test
    public void getMaxAndMin() {
        //求和
        int sum = list.stream().mapToInt(student -> student.score).sum();
        //求最大值
        int max1 = list.stream().mapToInt(student -> student.score).max().getAsInt();
        int max2;
        //list.stream().mapToInt(student->student.score).max().ifPresent(max->max2=max);
        System.out.println();
        System.out.println(sum);
    }

    @Test
    public void groupById() {
        /**
         *根据id分组List
         */
        Map<Integer, List<Student>> groupBy = list.stream().collect(groupingBy(x -> x.id));
        Map<Integer, List<Student>> groupBy2 = list.stream().collect(groupingBy(Student::getId));
        /**
         * 求和
         */
        int sum = list.stream().mapToInt(x -> x.score).sum();
        /**
         * 根据id分组成map
         */
        Map<Integer, Student> map = list.stream().collect(Collectors.toMap(x -> x.id, Function.identity()));
        Map<Integer, Student> map2 = list.stream().collect(Collectors.toMap(x -> x.id, x -> x));
        Map<Integer, Student> map3 = list.stream().collect(Collectors.toMap(Student::getId, Function.identity()));
        /**
         * 根据对象属性分类统计集合中对象某个属性的和
         */
        Map<Integer, Integer> sum2 = list.stream().collect(groupingBy(x -> x.id, Collectors.summingInt(x -> x.score)));
        /**
         * 根据名称统计id相同的个数
         */
        Map<String, Long> map1 = list.stream().collect(groupingBy(Student::getName, Collectors.counting()));
    }

    @Test
    public void demo3() {
        Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());

        LocalDate date = LocalDate.now();
        LocalDate dateFromClock = LocalDate.now(clock);
        System.out.println(date + "\t" + dateFromClock);

        LocalTime time = LocalTime.now();
        LocalTime timeFromClock = LocalTime.now(clock);
        System.out.println(time + "\t" + timeFromClock);

        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime dateTimeClock = LocalDateTime.now(clock);
        System.out.println(dateTime + "\t" + dateTimeClock);

        final LocalDateTime from = LocalDateTime.of(2017, Month.NOVEMBER, 28, 12, 0, 0);
        final LocalDateTime to = LocalDateTime.of(2017, Month.NOVEMBER, 29, 11, 0, 0);
        final Duration duration = Duration.between(from, to);
        System.out.println(duration.toString());
        System.out.println("days:" + duration.toDays());
        System.out.println("minutes:" + duration.toMinutes());
    }

    @Test
    public void base64Test() {
        final String text = "Base64 finally in Java8";

        final String encoded = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
        final String decoded = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
        System.out.println(encoded);
        System.out.println(decoded);
    }

    @Test
    public void parallelTest() {
        long[] array = new long[2000];

        Arrays.parallelSetAll(array, index -> ThreadLocalRandom.current().nextInt(100000));
        Arrays.stream(array).limit(10).forEach(i -> System.out.print(i + "\t"));
        System.out.println();

        Arrays.parallelSort(array);
        Arrays.stream(array).limit(10).forEach(i -> System.out.print(i + "\t"));
        System.out.println();
        Date date1 = new Date();

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date1);
        System.out.println(calendar2.getTime());
    }

    @Test
    public void dateTest() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2017, 10, 28, 12, 0);
        Date date1 = calendar1.getTime();
        Date date2 = new Date();

    }

    @Test
    public void demo05(){
        List<String > strs=new ArrayList<>();
        strs.add("aaa");
        System.out.println(strs.contains("aaa"));
    }

}
aaaaaaaaaaaaaaaaaaaaaaa
package java8.feature;

import java.util.*;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

public class FeatureTwo {
    List<Integer> list;

    @Before
    public void setup() {
        list= Lists.newArrayList(12,20,12,22,22,23,155,123);
        //list= Arrays.asList(12,11,23,11,23);
    }

    @Test
    public void demo() {
        /**
         * 统计list中对象的重复次数
         */
        Map<Integer, Long> map = list.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        map.forEach((k, v) -> System.out.println(k + "\t" + v));
        /**
         * 把偶数过滤掉
         */
        //list.stream().filter(x->x%2==1).forEach(System.out::println);
        //List<Integer> list1=list.stream().filter(x->x%2==0).collect(Collectors.toList());
        //list1.stream().forEach(x->System.out.println(x));
        //list1.stream().forEach(System.out::println);
        /**
         * 从小到大排序
         */
        //list.stream().sorted().forEach(System.out::println);
        //从大到小
        list.stream().sorted((x1, x2) -> x1 < x2 ? 1 : -1).forEach(System.out::println);
        /**
         * 偶数为true，奇数为false
         */
        list.stream().map(x -> x % 2 == 0).forEach(System.out::println);

    }

    @Test
    public void demo02() {
        /**
         *去重复元素
         */
        //list.stream().distinct().forEach(x->{
        //    System.out.println(x+1);
        //});
        List<Integer> distinct = list.stream().distinct().collect(Collectors.toList());
        distinct.forEach(System.out::println);
        /**
         *加上所有元素
         */
        int sum = list.stream().reduce((x1, x2) -> x1 + x2).get();
        System.out.println(sum);

        /**
         * 求最大，最小值
         */
        int max = list.stream().max(Comparator.comparingInt(x -> x)).get();
        int min = list.stream().min(Comparator.comparingInt(x -> x)).get();
        System.out.println("最大值：" + max + "\t" + "最小值：" + min);
    }

    @Test
    public void demo3() {
        System.out.println("最大：" + Collections.max(list));
        System.out.println("最小：" + Collections.min(list));
    }

}
aaaaaaaaaaaaaaaaaaaa
package java8.feature;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;

public class JodaDate {
    @Test
    public void date() {
        System.out.println(LocalDate.now());
        System.out.println(LocalDateTime.now());
        System.out.println(LocalTime.now());
    }
}
aaaaaaaaaaaaaaaaaaaaa
package java8.feature;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.primitives.Ints;
import org.junit.Test;

public class ListOperation {
    @Test
    public void demo01(){
        /**
         * 集合转数组
         */
        List<Integer> list=Collections.singletonList(1);
        int [] arr=list.stream().mapToInt(Integer::intValue).toArray();
        Ints.toArray(list);
        Ints.asList(arr);
        /**
         * 数组转集合
         */
        list= IntStream.of(arr).boxed().collect(Collectors.toList());
        /**
         * 单个数字转数组
         */
        arr= Stream.of(1).mapToInt(Integer::intValue).toArray();
        /**
         * BigDecimal转int
         */
        BigDecimal bigDecimal=new BigDecimal(3);
        int x=bigDecimal.multiply(new BigDecimal(100)).intValue();
        System.out.println(bigDecimal+"\t"+x);
    }
    @Test
    public void demo02(){
        List<Integer> list=Collections.singletonList(1);
        System.out.println(list.get(0));
    }
}
