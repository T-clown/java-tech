package java8.feature;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class FeatureTwo {
    List<Integer> list;

    @Before
    public void setup() {
        list = new ArrayList() {{
            add(12);
            add(20);
            add(12);
            add(22);
            add(22);
            add(23);
            add(159);
            add(12);
        }};
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
        list.stream().map(x -> x % 2 == 0 ? true : false).forEach(System.out::println);

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
        int max = list.stream().max((x1, x2) -> x1 > x2 ? 1 : -1).get();
        int min = list.stream().min((x1, x2) -> x1 > x2 ? 1 : -1).get();
        System.out.println("最大值：" + max + "\t" + "最小值：" + min);
    }

    @Test
    public void demo3() {
        System.out.println("最大：" + Collections.max(list));
        System.out.println("最小：" + Collections.min(list));
    }

    @Test
    public void demo8() {
        Date date1 = new Date();
        Date date2 = new Date(100000000000L);
        Date date3 = new Date(100000000000L);
        System.out.println(date1.toLocaleString());
        System.out.println(date2.toLocaleString());
        System.out.println(date2.before(date1));
        System.out.println(date1.compareTo(date2));
        System.out.println(date3.compareTo(date2));
        System.out.println(date2.compareTo(date1));
    }
}
