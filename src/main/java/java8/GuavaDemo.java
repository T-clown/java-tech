package java8;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.math.IntMath;

public class GuavaDemo {
    public static void main(String[] args) {
        //optional();
        strJoin();
        strSplit();
    }

    private static void optional() {
        Optional<Integer> possible = Optional.of(5);

        possible.isPresent(); // returns true

        possible.get(); // returns 5

        System.out.println(IntMath.mod(10, 6));
    }

    private static void strJoin() {
        Joiner joiner = Joiner.on("; ").skipNulls();
        String str1 = joiner.join("Harry", null, "Ron", "Hermione");
        String str2 = Joiner.on(",").skipNulls().join(Arrays.asList(1, 5, 7)); // returns "1,5,7"
        System.out.println(str1);
        System.out.println(str2);
    }

    /**
        omitEmptyStrings()	        从结果中自动忽略空字符串
        trimResults()	            移除结果字符串的前导空白和尾部空白
        trimResults(CharMatcher)	给定匹配器，移除结果字符串的前导匹配字符和尾部匹配字符
        limit(int)	                限制拆分出的字符串数量
     */
    private static void strSplit() {
        Iterable<String> iterable= Splitter.on(',').trimResults().omitEmptyStrings().split("foo,bar,,   qux");
        List<String> strList=Lists.newArrayList(iterable);
        strList.forEach(System.out::println);
    }

}
