package java8.feature;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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