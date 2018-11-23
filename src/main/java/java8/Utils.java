package java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.primitives.Ints;

public class Utils {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // 获取空字符串的数量
        long count = strings.parallelStream().filter(String::isEmpty).count();
        System.out.println(count);

        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }


    /**
     * 集合转数组
     *
     * @param ids
     * @return
     */
    public static List<Integer> arrayToList(int[] ids) {
        // return IntStream.of(ids).boxed().collect(Collectors.toList());
        return Ints.asList(ids);
    }

    /**
     * 集合转数组
     *
     * @param idList
     * @return
     */
    public static int[] listToArray(List<Integer> idList) {
        // return idList.stream().mapToInt(Integer::intValue).toArray();
        return Ints.toArray(idList);
    }

}
