package java8;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.junit.Test;

public class ExcelTest {

    @Test
    public void demo(){
        List<String> a= Arrays.asList("a","b","c");

        List<String> b= Arrays.asList("a","d","e");

        Collection<String> subtract = CollectionUtils.subtract(a, b);

        Collection<String> subtract2 = CollectionUtils.subtract(b, a);
        Collection<String> subtract3 = CollectionUtils.union(b, a);
        System.out.println();
        System.out.println(new Date(1605511284936L));

    }
}
