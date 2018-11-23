package java8;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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
        Predicate<Integer> predicate = x -> x > 5;
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
