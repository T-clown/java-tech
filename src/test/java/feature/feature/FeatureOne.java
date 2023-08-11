package feature.feature;

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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import feature.entity.Student;
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
        list.add(new Student(5, "", "", 4));
        list.add(new Student(1, "", "", 7));
    }

    @Test
    public void demo01() {
        Optional<Student> first = list.stream().filter(x -> x.getId() > 5).findFirst();
        boolean present = first.isPresent();
        Student student = first.get();
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
        Collections.sort(list, Comparator.comparing(x->x.id));
        System.out.println("最大值：" + Collections.max(list, Comparator.comparing(x->x.id)).score);
        System.out.println("最小值：" + Collections.min(list, Comparator.comparing(x->x.id)).score);
    }

    @Test
    public void getMaxAndMin() {
        //求和
        int sum = list.stream().mapToInt(student -> student.score).sum();
        //求最大值
        int max1 = list.stream().mapToInt(student -> student.score).max().getAsInt();
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

    }

    @Test
    public void demo03() {
        List<Integer> list= Lists.newArrayList(12,20,12,22,22,23,155,123);
        //list= Arrays.asList(12,11,23,11,23);
        System.out.println("最大：" + Collections.max(list));
        System.out.println("最小：" + Collections.min(list));

        /**
         * 求最大，最小值
         */
        int max = list.stream().max(Comparator.comparingInt(x -> x)).get();
        int min = list.stream().min(Comparator.comparingInt(x -> x)).get();
        System.out.println("最大值：" + max + "\t" + "最小值：" + min);

        /**
         *加上所有元素
         */
        int sum = list.stream().reduce((x1, x2) -> x1 + x2).get();
        System.out.println(sum);
    }


}
