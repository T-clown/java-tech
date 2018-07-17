package java8.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

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
        Iterator<Integer> iterator=list.iterator();
        while (iterator.hasNext()){
            int a=iterator.next();
            if(a==0){
                iterator.remove();
            }
           // System.out.println(a);
        }
        list.forEach(System.out::println);
    }

    @Test
    public void iterator2(){
        Iterator<Integer> iterator=list.listIterator();
        while (iterator.hasNext()){
            int a=iterator.next();
            if(a==0){
                iterator.remove();
            }
           // System.out.println(a);
        }
        list.forEach(System.out::println);

    }
}
