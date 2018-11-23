package java8;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import static java.util.stream.Collectors.groupingBy;

public class StreamDemo {
    public static List<Product> productList;

    static {
        productList = Lists.newArrayList();
        productList.add(new Product(1, "西瓜", 3, BigDecimal.TEN));
        productList.add(new Product(2, "东瓜", 5, BigDecimal.TEN));
        productList.add(new Product(3, "南瓜", 6, BigDecimal.TEN));
        productList.add(new Product(4, "香蕉", 8, BigDecimal.TEN));
        productList.add(new Product(5, "苹果", 10, BigDecimal.TEN));
    }

    public static void main(String[] args) {
        /**
         * id聚合
         */
        List<Integer> idList = productList.stream().map(x -> x.id).collect(Collectors.toList());
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

        Product product1 = productList.stream().min(Comparator.comparing(x -> x.id)).get();
        System.out.println(product1.id);
        Product product2 = productList.stream().max(Comparator.comparing(x -> x.stock)).get();
        System.out.println(product2.stock);
    }

}
