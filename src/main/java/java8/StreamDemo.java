package java8;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import entity.Product;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class StreamDemo {
    public static List<Product> productList;

    static {
        productList = Lists.newArrayList();
        productList.add(new Product(1, "西瓜", 12, BigDecimal.TEN));
        productList.add(new Product(2, "东瓜", 21, BigDecimal.TEN));
        productList.add(new Product(3, "南瓜", 11, BigDecimal.TEN));
        productList.add(new Product(4, "香蕉", 21, BigDecimal.TEN));
        productList.add(new Product(5, "苹果", 31, BigDecimal.TEN));
    }

    public static void main(String[] args) {
        Map<Integer, List<String>> groupList = productList.stream().collect(
            groupingBy(x -> x.stock, mapping(x -> x.name, toList())));
        System.out.println(groupList.size());
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
        Map<Integer, Long> groupSum = productList.stream().collect(Collectors
            .groupingBy(x -> x.id, Collectors.summingLong(x -> x.stock)));
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

}
