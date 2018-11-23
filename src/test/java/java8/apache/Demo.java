package java8.apache;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;

import java8.entity.Student;
import org.joda.time.DateTime;
import org.junit.Test;

public class Demo {
    @Test
    public void demo01(){
        System.out.println(2<<3);//2*8
        System.out.println(8>>2);//8/4
    }
    @Test
    public void demo02(){
        int x=1|2|4|6;
        System.out.println(4&4);
    }

    @Test
    public void demo03(){
        BigDecimal b=new BigDecimal(100.1);
        System.out.println(b.setScale(2, RoundingMode.UNNECESSARY));
    }

    private static String TICKET_TITLE_FMT = "%s(%s--%s处理超时)";

    private String getTicketTitle(String spuName, String rmaType, String rmaStatus) {
        return String.format(TICKET_TITLE_FMT, spuName, rmaType, rmaStatus);
    }

    @Test
    public void demo04(){
        String a="111";
        String b="222";
        String c="333";
        System.out.println(getTicketTitle(a,b,c));
    }


    @Test
    public void demo(){
        Date date=new Date();
        Date date1=new DateTime(date).plus(1).toDate();
        System.out.println(date.toString());
        System.out.println(date1.toString());
    }

    @Test
    public void demo4(){
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

        System.out.println(System.getProperties());
    }

    @Test
    public void demo5(){
        List<Student> list=new ArrayList<>();
        list.add(new Student(1,"1","1",1));
        list.add(new Student(2,"1","1",1));
        list.add(new Student(3,"1","1",1));
        list.add(new Student(1,"1","1",1));
        List<Integer> ids = list.stream()
            .filter(student -> student.id != 0)
            .mapToInt(student -> student.id)
            .distinct()
            .boxed()
            .collect(Collectors.toList());
        ids.forEach(System.out::println);
    }

    @Test
    public void demo6(){
        LocalDate localDate = LocalDate.now();
        LocalDateTime minTime = localDate.atTime(LocalTime.MIN);
        LocalDateTime maxTime = localDate.atTime(LocalTime.MAX);
        System.out.println(localDate.toString());
        System.out.println(minTime.toString());
        System.out.println(maxTime.toString());


        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime minTime1 = localDateTime.with(LocalTime.MIN);
        LocalDateTime maxTime1 = localDateTime.with(LocalTime.MAX);
        System.out.println(minTime1.toString());
        System.out.println(maxTime1.toString());
    }


    @Test
    public void demo7(){
        LocalDate now = LocalDate.now();
        LocalDate localDate = LocalDate.now().plusDays(3);

        LocalDateTime localDateTime=LocalDateTime.now();
        LocalDateTime localDateTime2=LocalDateTime.now().plusDays(3);

        System.out.println(now.until(localDate, ChronoUnit.DAYS));
        System.out.println(ChronoUnit.DAYS.between(now,localDate));
        System.out.println(ChronoUnit.DAYS.between(localDateTime,localDateTime2));
    }


}
