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
