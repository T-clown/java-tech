package mockito;

import java8.entity.Student;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import utils.DateUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alibaba.fastjson.JSON;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class Test01 {
    @Test
    public void demo01() {
        List mockedList = mock(List.class);

        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();

        LinkedList mockedList2 = mock(LinkedList.class);
        when(mockedList2.get(0)).thenReturn("first");
        when(mockedList2.get(1)).thenReturn(new RuntimeException());
        System.out.println(mockedList2.get(0));
        //System.out.println(mockedList2.get(1));
        System.out.println(mockedList2.get(999));
        verify(mockedList2).get(0);
    }

    @Test
    public void demo02() {
        List mockedList = mock(List.class);
        when(mockedList.get(anyInt())).thenReturn("element");

        // when(mockedList.contains(argThat(isValid()))).thenReturn("element");
        System.out.println(mockedList.get(999));
        verify(mockedList).get(anyInt());

        //verify(mock).someMethod(anyInt(), anyString(), eq("third argument"));
    }

    @Test
    public void demo03() {
        List mockedList = mock(List.class);

        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        // 下面的两个验证函数效果一样,因为verify默认验证的就是times(1)
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        // 验证具体的执行次数
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        // 使用never()进行验证,never相当于times(0)
        verify(mockedList, never()).add("never happened");

        // 使用atLeast()/atMost()
        verify(mockedList, atLeastOnce()).add("three times");
        //verify(mockedList, atLeast(2)).add("five times");
        verify(mockedList, atMost(5)).add("three times");

        doThrow(new RuntimeException()).when(mockedList).clear();

        // 调用这句代码会抛出异常
        mockedList.clear();
    }

    @Test
    public void demo04() {
        // A. 验证mock一个对象的函数执行顺序
        List singleMock = mock(List.class);

        //using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");

        // 为该mock对象创建一个inOrder对象
        InOrder inOrder = inOrder(singleMock);

        // 确保add函数首先执行的是add("was added first"),然后才是add("was added second")
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        // B .验证多个mock对象的函数执行顺序
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        // 为这两个Mock对象创建inOrder对象
        InOrder inOrder2 = inOrder(firstMock, secondMock);

        // 验证它们的执行顺序
        inOrder2.verify(firstMock).add("was called first");
        inOrder2.verify(secondMock).add("was called second");
    }

    @Test
    public void demo05() {
        List mock = mock(List.class);

        // when(mock.get(0)).thenThrow(new RuntimeException()).thenReturn("foo");

        when(mock.get(0)).thenReturn("one", "two", "three");
        // 第一次调用 : 抛出运行时异常
        System.out.println(mock.get(0));

        // 第二次调用 : 输出"foo"
        System.out.println(mock.get(0));

        // 后续调用 : 也是输出"foo"
        System.out.println(mock.get(0));
    }

    @Test
    public void streamTest() {
        Stream.iterate(1, i -> i++).limit(100).forEach(System.out::println);
        //jdk9
        //Stream.iterate(i->i<100,i->i+1).forEach(System.out::println);
    }

    @Test
    public void demt() {
        Student s1 = new Student();
        s1.setId(9);
        Student s2 = new Student();
        s2.setId(7);
        Student s3 = new Student();
        s3.setId(5);
        List<Student> list = new ArrayList<Student>() {{
            add(s2);
            add(s1);
            add(s3);
        }};
        //List<Student> list1= list.stream().sorted().collect(Collectors.toList());
        list.sort((o1, o2) -> {
            if (o1.id > o2.id) {
                return 1;
            } else if (o1.id < o2.id) {
                return -1;
            } else {
                return 0;
            }

        });
        System.out.println(list.get(0).id);
        System.out.println("234".equals("23"));
    }

    @Test
    public void calendarTest() {
        String firstDay;
        String lastDay;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获取前月的第一天
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        firstDay = format.format(cal_1.getTime());
        System.out.println("-----1------firstDay:" + firstDay);
        //获取前月的最后一天
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 0);//设置为1号,当前日期既为本月第一天
        lastDay = format.format(cale.getTime());
        System.out.println("-----2------lastDay:" + lastDay);

        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        System.out.println("===============first:" + first);
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        System.out.println("===============last:" + last);
    }

    @Test
    public void match() {
        //String str = "AWE3WE";
        //Pattern p = Pattern.compile("^[A-Z]+$");
        //Matcher m = p.matcher(str);
        //boolean isValid = m.matches();
        //System.out.println(isValid);
        //String str="1 23";
        //System.out.println(Pattern.matches("\\d+",str));
        //System.out.println(Integer.parseInt(str.replaceAll(" ","")));
        //System.out.println(StringUtils.isNumeric(str));
        List<String> list = new ArrayList<>();
        String str = "";
        list = JSON.parseArray(str, String.class);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void demo() {
        List<Student> students = new ArrayList<>();
        Student s1 = new Student();
        s1.date = new Date();
        s1.name = "我是";
        Student s2 = new Student();
        s2.name = "我是";
        s2.date = new Date(-1000);
        students.add(s1);
        students.add(s2);
        Set<String> strings = students.stream().map(Student::getName).collect(Collectors.toSet());
        strings.forEach(System.out::println);
    }

    @Test
    public void demoo(){

        System.out.println(DateUtil.DATE_FORMAT_TOOL.format(new Date()));
        System.out.println(RandomUtils.nextInt());
    }

}
