package feature;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class LambdaSort {
    public static void main(String[] args) {
        demo1();
    }

    private static  void  demo1(){
        List<Integer> names = Arrays.asList(1, 5, 2,6);
        /**
         * 从大到小排序
         */
        names.sort(Collections.reverseOrder());
        System.out.println(names);

        List<String> names2 = Arrays.asList("a", null, "c", "b", "d");
        /**
         * 从小到大
         */
        names2.sort(Comparator.nullsLast(String::compareTo));
        System.out.println(names2);

        /**
         * 非空判断
         */
        List<String> names3 = null;
        Optional.ofNullable(names3).ifPresent(list -> list.sort(Comparator.naturalOrder()));
        System.out.println(names3);

        List<BigDecimal> bigDecimals = Arrays.asList(BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.TEN);
        bigDecimals.sort((x,y)->y.compareTo(x));
        bigDecimals.sort((x,y)->x.compareTo(y));

    }
}