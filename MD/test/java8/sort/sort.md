package java8.sort;

import java.util.Random;

public class BubbleSort {
    public static void main(String[] args) {
        int[] a = {12, 20, 5, 16, 15, 1, 30, 45, 23, 9};
        BubbleSort sort = new BubbleSort();
        //int[] a = sort.get();
        long start = System.currentTimeMillis();
        sort.sort(a);
        System.out.println("时间：" + (System.currentTimeMillis() - start));
        for (int i = 0; i < a.length-1; i++) {
            System.out.println(a[i]);
        }
    }

    public void sort(int[] a) {
        for (int i = 0; i < a.length-1; i++) {
            for (int j = i + 1; j < a.length-1; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    public int[] get() {
        int[] a = new int[10000];
        Random r = new Random(10000);
        for (int i = 0; i < a.length-1; i++) {
            a[i] = r.nextInt();
        }
        return a;
    }
}
aaaaaaaaaaaaaaaaaaaa
package java8.sort;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.primitives.Ints;
import java8.entity.Student;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

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
aaaaaaaaaaaaaaaaaaaaaaaaa
package java8.sort;

public class FastSort {
    public static void main(String[] args) {
        System.out.println("Hello World");
        int[] a = {12, 20, 5, 16, 15, 1, 30, 45, 23, 9};
        int start = 0;
        int end = a.length - 1;
        long startTime = System.currentTimeMillis();
        sort(a, start, end);
        System.out.println("时间：" + (System.currentTimeMillis() - startTime));
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

    }

    private static void sort(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int key = a[low];

        while (end > start) {
            //从后往前比较
            while (end > start && a[end] >= key)  //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
            { end--; }
            if (a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            //从前往后比较
            while (end > start && a[start] <= key)//如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
            { start++; }
            if (a[start] >= key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        if (start > low) {
            sort(a, low, start - 1);//左边序列。第一个索引位置到关键值索引-1
        }
        if (end < high) {
            sort(a, end + 1, high);//右边序列。从关键值索引+1到最后一个
        }
    }
}
