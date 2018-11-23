package java8.compare;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import java8.entity.Student;
import org.junit.Before;
import org.junit.Test;

public class Compare {
    List<Student> list;

    @Before
    public void setList() {
        Student s1 = new Student();
        s1.id = 1;
        Student s2 = new Student();
        s2.id = 2;
        Student s3 = new Student();
        s3.id = 3;
        list.add(s1);
        list.add(s2);
        list.add(s3);
    }

    @Test
    public void demo() {
        Student s1 = new Student();
        s1.id = 1;
        Student s2 = new Student();
        s2.id = 2;
        System.out.println(s1.compareTo(s2));
    }

    @Test
    public void sort() {
        list.sort(Comparator.comparingInt(o -> o.id));
    }
}
