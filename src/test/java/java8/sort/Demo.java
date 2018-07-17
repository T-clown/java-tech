package java8.sort;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java8.entity.Student;
import org.junit.Test;

public class Demo {
    @Test
    public void demo() {
        Map<Integer, String> map = new HashMap<>(16);
        map.put(1, "1");
        map.put(2, "23");
        List<String> list = new ArrayList<>(map.values());
        list.forEach(System.out::println);
    }

    @Test
    public void demo2() {
        Student s1 = new Student();
        s1.high = BigDecimal.TEN;
        Student s2 = new Student();
        s2.high = BigDecimal.TEN;
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        BigDecimal a=students.stream().map(Student::getHigh).reduce(BigDecimal.ZERO, BigDecimal::add);
        int bigDecimals = students.stream().mapToInt(x -> x.getHigh().multiply(
            BigDecimal.valueOf(100)).intValue()).sum();
        BigDecimal b=new BigDecimal(2.00);
        System.out.println(b);
        System.out.println(b.setScale(0, RoundingMode.HALF_UP) );
        System.out.println(a);
        System.out.println(bigDecimals);
    }

}
