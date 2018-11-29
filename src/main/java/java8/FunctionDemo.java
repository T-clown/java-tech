package java8;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import entity.Product;

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
