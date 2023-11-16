package feature;

import com.alibaba.fastjson2.JSON;
import io.vavr.Function2;
import io.vavr.collection.List;
import io.vavr.control.Try;

import static io.vavr.API.*;

/**
 * https://docs.vavr.io/#_side_effects
 * https://juejin.cn/post/6854573219467411470
 */
public class VavrApp {
    public static void main(String[] args) {
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        System.out.println(sum.apply(1));
        System.out.println(sum.apply(1,2));


        List<Integer> map = List.of(1, 2, 3, 4, 5).filter(i -> i > 3).map(i -> i * 2);


        List<Integer> list = io.vavr.collection.List.of(1, 2);
        List<Integer> list2 = list.appendAll(List.of(3, 4)).appendAll(List.of(5, 6)).append(7);
        System.out.println(JSON.toJSONString(list));
        System.out.println(JSON.toJSONString(list2));


        Try.of(() -> 1 / 0)
                .andThen(r -> System.out.println("and then " + r))
                .onFailure(error -> System.out.println("failure" + error.getMessage()))
                .andFinally(() -> {
                    System.out.println("finally");
                });

        var error = Try.of(() -> 0 / 0);
        System.out.println(error.getClass());
        // 合法的加法，构建出 Success
        var normal = Try.of(() -> 1 + 1);
        System.out.println(normal.getClass());


        System.out.println(bmiFormat(1,10));
    }

    public static String bmiFormat(int height, int weight) {
        int bmi = weight / (height * height);
        return Match(bmi).of(
                // else if (bmi < 18.5)
                Case($(v -> v < 18), () -> "有些许晃荡！"),
                // else if (bmi < 25)
                Case($(v -> v < 25), () -> "继续加油哦！"),
                // else if (bmi < 30)
                Case($(v -> v < 30), () -> "你是真的稳！"),
                // else
                Case($(), () -> "难受！")
        );

    }

}
