package java8.sort;

import java8.entity.Student;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    private Integer a;


    @Test
    public void demo(){
        int n=16;
        n|=n>>>2;
        System.out.println(n);
        System.out.println(Double.parseDouble("1")>0);

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

    @Test
    public void aVoid(){
        long value=-1L;
        value = value < 0 ? Math.abs(value) : value;
        System.out.println(value);
    }

}