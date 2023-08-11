package feature;

import patterns.adapter.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Scanner;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

/**
 * 新特性
 */
public class JdkNewFeature {
    public static void main(String[] args) {
        switchMatch();
        random();
        annotation();
    }

    /**
     * jdk14&jdk15
     * switch匹配
     */
    public static void switchMatch() {
        var score = 'C';
        String s = switch (score) {
            case 'A' -> "优秀";
            case 'B' -> "良好";
            case 'C' -> "中";
            case 'D' -> "及格";
            case 'F' -> "不及格";
            default -> "成绩输入错误";
        };
        System.out.println(s);
        //多值匹配
        String s2 = switch (score) {
            case 'A', 'B' -> "上等";
            case 'C' -> "中等";
            case 'D', 'E' -> "下等";
            default -> "成绩数据输入非法！";
        };
        System.out.println(s2);
    }

    /**
     * 增强的伪随机数生成器
     * jdk17
     */
    public static void random() {
        RandomGeneratorFactory<RandomGenerator> factory = RandomGeneratorFactory.of("L128X256MixRandom");
        // 使用时间戳作为随机数种子
        RandomGenerator randomGenerator = factory.create(System.currentTimeMillis());
        // 生成随机数
        int i = randomGenerator.nextInt(10);
        System.out.println(i);
    }

    /**
     * jdk17
     *
     * @param o
     */
    public static void typeMatch(Object o) {
        if (o instanceof String s) {
            System.out.println(s);
        }

        String v = switch (o) {
            case Integer i -> String.format("int %d", i);
            case Long l -> String.format("long %d", l);
            case Double d -> String.format("double %f", d);
            case String s -> String.format("String %s", s);
            default -> o.toString();
        };
        System.out.println(v);
    }


    @Retention(RetentionPolicy.RUNTIME)
    @interface Hints {
        Hint[] value();
    }

    @Repeatable(Hints.class)
    @interface Hint {
        String value();
    }

    @Hint("hint1")
    @Hint("hint2")
    class Dog {

    }

    /**
     * jdk8
     */
    public static void annotation() {
        Hint hint = Dog.class.getAnnotation(Hint.class);
        System.out.println(hint);                   // null
        Hints hints1 = Dog.class.getAnnotation(Hints.class);
        System.out.println(hints1.value().length);  // 2

        Hint[] hints2 = Dog.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2.length);          // 2

    }

    /**
     * jdk9
     * @throws FileNotFoundException
     */
    public static void tryResource() throws FileNotFoundException {
        final Scanner scanner = new Scanner(new File("testRead.txt"));
        PrintWriter writer = new PrintWriter(new File("testWrite.txt"));
        try (scanner; writer) {

        }
    }


}
