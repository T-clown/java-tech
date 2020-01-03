package java8.iterator;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IteratorTest {
    private List<Integer> list;

    @Before
    public void setup(){
        list=new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
    }
    @Test
    public void iterator(){
        //list.removeIf(a -> a == 0);
        // System.out.println(a);
        list.removeIf(a -> a == 0);
        list.forEach(System.out::println);
    }

    @Test
    public void iterator2(){
        // System.out.println(a);
        list.removeIf(a -> a == 0);
        list.forEach(System.out::println);

    }
}