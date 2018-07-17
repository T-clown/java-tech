package java8.utils;

import java.util.Comparator;

import java8.entity.Student;

public class DateComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return (Integer.compare(o1.score, o2.score));
    }
}
