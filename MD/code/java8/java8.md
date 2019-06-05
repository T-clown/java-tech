package java8;
@FunctionalInterface
public interface Factory<T> {
    T product();
}
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa

package java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import entity.Product;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * Supplier<T>: 数据提供器，可以提供 T 类型对象；无参的构造器，提供了 get 方法；

 Function<T,R>: 数据转换器，接收一个 T 类型的对象，返回一个 R类型的对象； 单参数单返回值的行为接口；提供了 apply, compose, andThen, identity 方法；

 Consumer<T>: 数据消费器， 接收一个 T类型的对象，无返回值，通常用于设置T对象的值； 单参数无返回值的行为接口；提供了 accept, andThen 方法；

 Predicate<T>: 条件测试器，接收一个 T 类型的对象，返回布尔值，通常用于传递条件函数； 单参数布尔值的条件性接口。提供了 test (条件测试) , and-or- negate(与或非) 方法。

 https://mp.weixin.qq.com/s/Tnp6kbh7m41jU53XCxpufw

 https://mp.weixin.qq.com/s/Gueh64eJ1RmVgMAoOaoTzg
 */
public class FunctionDemo {
    public static void main(String[] args) {
        Product product=result.apply(222);
        function();
        predicate();
        bifunction();
        consumer();
        supplier();
        factory();
        System.out.println(product.id);
    }

    private static Function<Integer,Product> result=request->{
        Product product=new Product();
        product.id=request;
        return product;
    };

    /**
     * Function< T, R > 接收T对象，返回R对象
     */
    private static void function(){
        Function<Integer, Integer> name = e -> e * 2;
        Function<Integer, Integer> square = e -> e * e;
        //返回一个先执行当前函数对象apply方法再执行after函数对象apply方法的函数对象。
        int value = name.andThen(square).apply(3);
        System.out.println("andThen value=" + value);
        //返回一个先执行before函数对象apply方法再执行当前函数对象apply方法的函数对象
        int value2 = name.compose(square).apply(3);
        System.out.println("compose value2=" + value2);
        //返回一个执行了apply()方法之后只会返回输入参数的函数对象
        Object identity = Function.identity().apply("huohuo");
        System.out.println(identity);
    }

    /**
     * Predicate< T > 接收T对象并返回boolean
     */
    private static void predicate(){
        // 数字类型 判断值是否大于5
        Predicate<Integer> predicate = x -> {
           return x > 5;
        };

        System.out.println(predicate.test(10));//true

        // 字符串的非空判断
        Predicate<String> predicateStr = x -> null == x && "".equals(x);
        System.out.println(predicateStr.test(""));//false

    }

    /**
     * BiFunction<T, U, R> 接收T,U对象，返回R对象
     */
    private static void bifunction(){
        BiFunction<Integer,Integer,String> biFunction=(a,b)->{
            String str="求和：";
            return str+(a+b);
        };
        System.out.println(biFunction.apply(2,3));
    }

    /**
     * Consumer< T >  接收T对象，不返回值
     */
    private static void consumer(){
        Consumer<Integer> consumer=x->{
            System.out.println(x+"aaa");
        };
        consumer.accept(1);
    }

    /**
     * Supplier< T >  提供T对象（例如工厂），不接收值
     */
    private static void supplier(){
        Supplier<String> supplier=()->{
            String str="返回值为：";
            return str+"字符串";
        };
        System.out.println(supplier.get());
    }
    private static void factory(){
        Factory<String> factory=()->{
            String str="函数式编程：";
            return str+"factory";
        };
        System.out.println(factory.product());
    }


}
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa

package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionUtil {
    public static <T, R> List<R> multiGetResult(List<Function<List<T>, R>> functions, List<T> list) {

        return functions.stream().map(f -> f.apply(list)).collect(Collectors.toList());

    }

    public static void main(String[] args) {

        System.out.println(multiGetResult(

            Arrays.asList(

                list -> list.stream().collect(Collectors.summarizingInt(x -> x)),

                list -> list.stream().filter(x -> x < 50).sorted().collect(Collectors.toList()),

                list -> list.stream().collect(Collectors.groupingBy(x -> (x % 2 == 0 ? "even" : "odd"))),

                list -> list.stream().sorted().collect(Collectors.toList()),

                list -> list.stream().sorted().map(Math::sqrt).collect(Collectors.toMap(x -> x, y -> Math.pow(2, y)))),

            Arrays.asList(64, 49, 25, 16, 9, 4, 1, 81, 36)));

    }
}

aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa

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

aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa

package java8;

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
    }
}
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
package java8;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.alibaba.fastjson.JSON;

import com.google.common.collect.Lists;
import entity.Product;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

public class StreamDemo {
    public static List<Product> productList;

    static {
        productList = Lists.newArrayList();
        productList.add(new Product(1, "西瓜", 1, 1));
        productList.add(new Product(2, "东瓜", 2, 1));
        productList.add(new Product(1, "南瓜", 11, 1));
        productList.add(new Product(2, "香蕉", 12, 1));
        productList.add(new Product(5, "苹果", 10, 1));
    }

    public static void main(String[] args) {
     Map<Integer,Long> map=  productList.stream().collect(groupingBy(x -> x.id, Collectors.mapping(x -> x.stock, Collectors.summingLong(Integer::longValue))));
        List<Map<String, Object>> list=Lists.newArrayList();
        list.stream().collect(groupingBy(x -> String.valueOf(x.get("")), Collectors.mapping(x ->String.valueOf(x.get("")), Collectors.summingLong(
            Long::parseLong))));
        //Map<String, Object> map = list.parallelStream()
        //    .collect(Collectors.groupingBy(x -> String.valueOf(x.get("channel")),
        //        Collectors.mapping(x -> String.valueOf(x.get("charge")),
        //            Collectors.summarizingLong(x -> Long.valueOf(x)))));
        System.out.println(JSON.toJSONString(map));
        double d = 114.145;
         d = (double) Math.round(d * 100) / 100;
         System.out.println(d);
        System.out.println(new BigDecimal(1).divide(new BigDecimal(20)));

    }

    private static void max() {
        /**
         * 根据某一属性分组后再根据另一属性求出每组中最大的
         */
        List<Product>  p=  productList.stream().collect(Collectors.groupingBy(x->x.id)).values().stream().map(x->x.stream().max(Comparator.comparing(y->y.stock)).get()).collect(Collectors.toList());
        int max = productList.stream().collect(
            Collectors.groupingBy(x -> x.id)).values().stream().mapToInt(x -> x.stream().mapToInt(y -> y.stock).max()
            .orElse(0)).sum();
        int min = productList.stream().collect(Collectors.groupingBy(x -> x.id)).values().stream().mapToInt(
            x -> x.stream().mapToInt(y -> y.stock).min().orElse(0)).sum();
        System.out.println("最大：" + max);
        System.out.println("最小：" + min);
    }

    private static void listUtils() {
        List<String> as = Arrays.asList("a", "b");
        List<String> bs = Arrays.asList("1", "2", "3");
        HashMap<String, String> hashMap = new LinkedHashMap<>(50);
        int max = Math.max(as.size(), bs.size());
        IntStream.range(0, max).forEach(i -> hashMap.put(
            String.valueOf(i >= as.size() ? "defaultA" : as.get(i)),
            String.valueOf(i >= as.size() ? "defaultB" : bs.get(i))
            )
        );

        hashMap.keySet().forEach(System.out::println);
        System.out.println();
        hashMap.values().forEach(System.out::println);
    }

    private static void getByMax() {

        /**
         * 根据某一属性聚合另一个属性
         */
        productList.stream().collect(
            groupingBy(x -> x.stock, mapping(x -> x.name, toList())));

        Map<Integer, List<String>> groupList = productList.stream().collect(
            groupingBy(x -> x.stock, Collectors.mapping(x -> x.name, toList())));
        /**
         * 根据某一属性排序
         */
        productList.sort(Comparator.comparing(x -> x.stock));
        List<Product> products = productList.stream().sorted(Comparator.comparingInt(x -> x.stock)).collect(
            toList());

        /**
         * 根据最大属性值获取
         */
        Product product = productList.stream().max(Comparator.comparing(x -> x.stock)).get();
        Optional<Product> optionalProduct = productList.stream().max(Comparator.comparing(x -> x.stock));
        Product product2 = optionalProduct.orElseGet(null);

    }

    /**
     * 创建Stream
     */
    private static void createStream() {
        Stream.of(productList);
        productList.stream();
        Arrays.stream(new int[] {1, 2, 3});
        Stream.builder();
        Stream<Integer> iterate = Stream.iterate(0, x -> x + 1).limit(3);
        Stream<String> generate = Stream.generate(() -> "hello world").limit(3);
    }

    private static void streamDemo() {
        /**
         * id聚合
         */
        List<Integer> idList = productList.stream().map(x -> x.id).collect(toList());
        /**
         * 聚合成map，集合中无重复
         */
        Map<Integer, Product> productMap = productList.stream().collect(
            Collectors.toMap(x -> x.id, Function.identity()));
        /**
         * 聚合，集合中有重复
         */
        Map<Integer, List<Product>> groupList = productList.stream().collect(groupingBy(x -> x.id));

        /**
         * 根据对象属性分类统计集合中对象某个属性的和
         */
        Map<Integer, Long> groupSum = productList.stream().collect(
            Collectors.groupingBy(x -> x.id, Collectors.summingLong(x -> x.stock)));
        /**
         * 统计spu下已选参团的sku单元个数
         */
        Map<Integer, Long> groupCount = productList.stream().collect(groupingBy(Product::getId, Collectors.counting()));
        /**
         * 统计所有库存
         */
        int sumStock = productList.stream().mapToInt(Product::getStock).sum();
        /**
         * 最小
         */
        int minStock = productList.stream().mapToInt(Product::getStock).min().getAsInt();
        /**
         * 最大
         */
        int maxStock = productList.stream().mapToInt(Product::getStock).max().getAsInt();
        /**
         * 排序
         */
        productList.sort(Comparator.comparing(x -> x.id));
        /**
         * 某一属性最大或者最小
         */
        Product product1 = productList.stream().min(Comparator.comparing(x -> x.id)).get();
        Product product2 = productList.stream().max(Comparator.comparing(x -> x.stock)).get();
    }

    /**
     * 根据对象某一属性去重，对象不用重写 equals 和 hashCode 方法
     */
    private static void distinct() {
        List<Product> list = productList.stream().collect(collectingAndThen(
            toCollection(() -> new TreeSet<>(Comparator.comparingInt(Product::getId))), ArrayList::new));
        list.forEach(System.out::println);
    }

    private static void intStream() {
        IntStream.range(1, 5).forEach(System.out::println);
        System.out.println();
        IntStream.rangeClosed(1, 5).forEach(System.out::println);
    }

    private static void streamOperation() {
        /**
         * allMatch(Predicate<? super T> predicate)：全部匹配
         * anyMatch：有一个匹配就返回true
         * noneMatch：全部都不匹配才返回true
         */
        System.out.println(Stream.of("peter", "anna", "mike").allMatch(s -> s.startsWith("a")));
        /**
         * filter(Predicate<? super T> predicate)：过滤操作，返回Stream
         */
        Stream.of("peter", "anna", "mike").filter(value -> value.startsWith("a")).collect(Collectors.toList()).forEach(
            System.out::println);
        /**
         * map：映射操作，返回Stream
         */
        Stream.of("peter", "anna", "mike").map(String::toUpperCase).collect(Collectors.toList()).forEach(
            System.out::println);
        /**
         * flatMap：将最底层元素抽出来放到一起
         */
        Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(2, 3, 6)).flatMap(x -> x.stream()).collect(Collectors.toList())
            .forEach(System.out::print);

        Stream<List<Integer>> listStream = Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(2, 3, 6));
        listStream.flatMap(Collection::stream).collect(Collectors.toList()).forEach(System.out::print);
        /**
         * concat：流连接操作
         */
        Stream.concat(Stream.of(1, 2), Stream.of(3)).forEach(System.out::print);
        /**
         * peek：生成一个包含原Stream的所有元素的新Stream，新Stream每个元素被消费之前都会执行peek给定的消费函数
         */
        Stream.of(2, 4).peek(x -> System.out.print(x - 1)).forEach(System.out::print);
        /**
         * skip：跳过前N个元素后，剩下的元素重新组成一个Stream
         */
        Stream.of(1, 2, 3, 4).skip(2).forEach(System.out::print);
        /**
         * max：最大值，存在求最大值，肯定就有求最小值。min
         */
        System.out.println(Stream.of(1, 2, 3, 4).max(Integer::compareTo).get());
        System.out.println(Stream.of(1, 5, 3, 4).max(Comparator.naturalOrder()).get());
        System.out.println(Stream.of(1, 5, 3, 4).min(Comparator.reverseOrder()).get());

        /**
         * reduce：网上翻译为规约，用途比较广，可以作为累加器，累乘器，也可以用来实现map、filter操作。
         *
         *  Optional<T> reduce(BinaryOperator<T> accumulator);
         *  T reduce(T identity, BinaryOperator<T> accumulator);
         *  <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);
         */
        System.out.println(Stream.of(1, 2, 3).reduce((a, b) -> a + b).get());
        System.out.println(Arrays.asList(1, 2, 3).stream().reduce(0, (a, b) -> a + b));
        /**
         * 把数据分成两部分
         */
        Map<Boolean, List<Integer>> collect1 = Stream.of(1, 3, 4).collect(Collectors.partitioningBy(x -> x > 2));
        Map<Boolean, Long> collect2 = Stream.of(1, 3, 4).collect(
            Collectors.partitioningBy(x -> x > 2, Collectors.counting()));
        /**
         *Collectors.joining(",")：拼接字符串
         */
        System.out.println(Stream.of("a", "b", "c").collect(Collectors.joining(",")));
        /**
         * Collectors.collectingAndThen(Collectors.joining(","), x -> x + "d")：
         * 先执行collect操作后再执行第二个参数的表达式。这里是先拼接字符串，再在最后+ "d"
         */
        String str = Stream.of("a", "b", "c").collect(
            Collectors.collectingAndThen(Collectors.joining(","), x -> x + "d"));
        /**
         * Collectors.mapping(...)：跟map操作类似，只是参数有点区别
         */
        System.out.println(
            Stream.of("a", "b", "c").collect(Collectors.mapping(x -> x.toUpperCase(), Collectors.joining(","))));
        System.out.println(Stream.of("a", "b", "c").map(String::toUpperCase).collect(Collectors.joining(",")));

    }

    /**
     * reduce实现filter功能方式
     * reduce为什么每次都new一次ArrayList
     * 因为reduce规定第二个参数BiFunction<U, ? super T, U> accumulator表达式不能改变其自身参数acc原有值，
     * 所以每次都要new ArrayList<T>(acc)，再返回新的list。
     */
    public static <T> List<T> reduceFilter(Stream<T> stream, Predicate<T> predicate) {
        return stream.reduce(new ArrayList<>(), (acc, t) -> {
            if (predicate.test(t)) {
                List<T> lists = new ArrayList<T>(acc);
                lists.add(t);
                return lists;
            }
            return acc;
        }, (List<T> left, List<T> right) -> {
            List<T> lists = new ArrayList<T>(left);
            lists.addAll(right);
            return lists;
        });
    }

    /**
     * collect实现filter功能方式
     */
    public static <T> List<T> collectFilter(Stream<T> stream, Predicate<T> predicate) {
        return stream.collect(ArrayList::new, (acc, t) -> {
            if (predicate.test(t)) { acc.add(t); }
        }, ArrayList::addAll);

    }

}
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
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
