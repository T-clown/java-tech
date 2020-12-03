
package feature;

import java.util.Comparator;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

/**
 * Supplier<T>: 数据提供器，可以提供 T 类型对象；无参的构造器，提供了 get 方法；
 * Function<T,R>: 数据转换器，接收一个 T 类型的对象，返回一个 R类型的对象； 单参数单返回值的行为接口；提供了 apply, compose, andThen, identity 方法；
 * Consumer<T>: 数据消费器， 接收一个 T类型的对象，无返回值，通常用于设置T对象的值； 单参数无返回值的行为接口；提供了 accept, andThen 方法；
 * Predicate<T>: 条件测试器，接收一个 T 类型的对象，返回布尔值，通常用于传递条件函数； 单参数布尔值的条件性接口。提供了 test (条件测试) , and-or- negate(与或非) 方法。
 * https://mp.weixin.qq.com/s/Tnp6kbh7m41jU53XCxpufw
 * https://mp.weixin.qq.com/s/Gueh64eJ1RmVgMAoOaoTzg
 */
public class FunctionApp {
    public static void main(String[] args) {
        //function();
        //predicate();
        //bifunction();
        //consumer();
        //biconsumer();
        //supplier();
        //factory();
        binaryOperator();
        ;
    }

    /**
     * Function< T, R > 接收T对象，返回R对象
     */
    private static void function() {
        Function<Integer, Integer> multiplyTwo = e -> e * 2;
        Function<Integer, Integer> square = e -> e * e;
        System.out.println("multiplyTwo apply value=" + multiplyTwo.apply(5));
        //返回一个先执行当前函数对象apply方法再执行after函数对象apply方法的函数对象。
        int value = multiplyTwo.andThen(square).apply(3);
        System.out.println("andThen value=" + value);
        //返回一个先执行before函数对象apply方法再执行当前函数对象apply方法的函数对象
        int composeValue = multiplyTwo.compose(square).apply(3);
        System.out.println("compose composeValue=" + composeValue);
        //返回一个执行了apply()方法之后只会返回输入参数的函数对象
        Object identity = Function.identity().apply("identity");
        System.out.println(identity);
    }

    /**
     * Predicate< T > 接收T对象并返回boolean
     */
    private static void predicate() {
        // 数字类型 判断值是否大于5
        Predicate<Integer> predicate = x -> x > 5;
        //true
        System.out.println(predicate.test(10));
        // 字符串的非空判断
        Predicate<String> predicateStr = StringUtils::isNotBlank;
        //false
        System.out.println(predicateStr.test("6"));
    }

    private static void bipredicate() {
        BiPredicate<Integer, String> predicate = (x, y) -> x > 5 && StringUtils.isNotBlank(y);
        System.out.println(predicate.test(1,"6"));
    }

    /**
     * BiFunction<T, U, R> 接收T,U对象，返回R对象
     */
    private static void bifunction() {
        BiFunction<Integer, Integer, Integer> biFunction = (a, b) -> a + b;
        Function<Integer, Integer> function = x -> x * x;
        Integer value = biFunction.andThen(function).apply(5, 10);
        System.out.println(value);
    }

    private static void binaryOperator() {
        Comparator<Integer> comparator = Comparator.comparingInt(x -> x);
        System.out.println(BinaryOperator.minBy(comparator).apply(1, 2));
        System.out.println(BinaryOperator.maxBy(comparator).apply(1, 2));
    }

    /**
     * Consumer< T >  接收T对象，不返回值
     */
    private static void consumer() {
        Map<String, String> map = Maps.newHashMap();
        Consumer<String> consumer = x -> map.put(x, x);
        consumer.accept("hello");
        System.out.println(map.get("hello"));
    }

    private static void biconsumer() {
        BiConsumer<Integer, Integer> biConsumer = (a, b) -> System.out.println(a + b);
        BiConsumer<Integer, Integer> then = (a, b) -> System.out.println(a * b);
        biConsumer.andThen(then).accept(1, 2);
    }

    /**
     * Supplier< T >  提供T对象（例如工厂），不接收值
     */
    private static void supplier() {
        Supplier<String> supplier = () -> {
            String str = "返回类型为：";
            return str + "String";
        };
        System.out.println(supplier.get());
    }

    private static void factory() {
        Factory<String> factory = () -> {
            String str = "函数式编程：";
            return str + "factory";
        };
        System.out.println(factory.product());
    }

}