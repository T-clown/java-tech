package mockito;

import com.google.common.collect.Maps;
import java8.entity.Student;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import utils.DateUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void demoo(){

        //System.out.println(DateUtil.DATE_FORMAT_TOOL.format(new Date()));
        //System.out.println(RandomUtils.nextInt());

        Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(2, 3, 6)).flatMap(x -> x.stream()).collect(Collectors.toList())
            .forEach(System.out::println);
        LocalDate now = LocalDate.now();
        Date minDate = now.dayOfMonth().withMinimumValue().toDate();
        Date maxDate = now.dayOfMonth().withMaximumValue().toDate();
        System.out.println(minDate.toString());
        System.out.println(maxDate.toString());
    }

}
aaaaaaaaaaaaaaaaaaaaaaaaaa
package mockito;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;

import com.google.common.collect.Lists;
import java8.entity.Student;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static java.util.stream.Collectors.groupingBy;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class Test02 {
    @Mock
    private List list;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void demo01() {
        when(list.get(anyInt())).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            int a = invocation.getArgument(0);
            int b = (int)args[0];
            //Object result = invocation.callRealMethod();
            Method method = invocation.getMethod();
            Object object = invocation.getMock();
            System.out.println("Method:" + method);
            System.out.println("mock:" + object);
            return "hello" + a + "=" + b + ":";
        });
        System.out.println(list.get(666));
    }

    @Test
    public void demo02() {
        when(list.get(anyInt())).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                Integer a = invocation.getArgument(0);
                return a;
            }
        });
        System.out.println(list.get(999));

    }

    @Test
    public void demo03() {
        doThrow(new RuntimeException()).when(list).get(0);
        doReturn(666).when(list).get(666);
        //doCallRealMethod().when(list).get(0);
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                return invocation.getArgument(0);
            }
        }).when(list).get(222);
        doNothing().doThrow(new RuntimeException()).when(list).get(333);
        System.out.println(list.get(666));
        System.out.println(list.get(222));
    }

    @Test
    public void demo04() {
        List list1 = new LinkedList();
        List spy = spy(list1);
        //doNothing().when(spy).clear();

        //when(spy.get(0)).thenReturn("two");
        doReturn(3).when(spy).get(0);
        System.out.println(spy.get(0));
        System.out.println(list1.size());
    }

    @Test
    public void demo05() {
        long c = 6 * 24 * 60 * 60 * 1000 + 1000;
        long a = TimeUnit.MILLISECONDS.toDays(c);
        System.out.println(a);
    }

    @Test
    public void demo06() {
        System.out.println(
            TimeUnit.SECONDS.toHours(60 * 60));
        ConcurrentHashMap hashMap = new ConcurrentHashMap();
    }

    @Test
    public void demo07() {
        Calendar time = Calendar.getInstance();
        time.setTime(new Date());
        time.add(Calendar.DATE, -30);
        System.out.println(time.getTime().toString());
        System.out.println(DateUtils.addDays(new Date(), -30).toString());
    }

    @Test
    public void demooo() {
        List<Student> list = new ArrayList<>();
        Map<Integer, Long> twoLevelCount = list.stream().collect(groupingBy(x -> x.id, Collectors.counting()));
        System.out.println(list);
    }

    @Test
    public void demo3() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        String ip = addr.getHostAddress().toString(); //获取本机ip
        String hostName = addr.getHostName().toString(); //获取本机计算机名称
        System.out.println(ip);
        System.out.println(hostName);
    }

    @Test
    public void demo4() {
        BigDecimal bg = new BigDecimal(2.333);
        BigDecimal b = bg.setScale(2, BigDecimal.ROUND_DOWN);
        // bg.setScale(2, RoundingMode.UNNECESSARY);
        System.out.println(b);

    }

    @Test
    public void aa() {
        List<BigDecimal> list = new ArrayList<>();
        list.add(BigDecimal.TEN);
        list.add(BigDecimal.ONE);
        //list.add(null);
        // 将user对象的mongey取出来map为Bigdecimal
        BigDecimal a = list.stream().reduce(BigDecimal.TEN, BigDecimal::add);
        System.out.println(a);
    }

    @Test
    public void demoddd() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add("1");
        list1.add("6");
        list2.add("2");
        map.put("a", list1);
        map.put("b", list2);
        map.put("c", null);
        List<String> list = new ArrayList<>();
        map.values().forEach(list::addAll);
        list.forEach(System.out::println);
    }

    @Test
    public void demo12() {
        List<String> strList = Lists.newArrayList();
        strList.add("aaa");
        strList.add("1");
        String[] strings = strList.toArray(new String[0]);
        System.out.println(strings.length);
        System.out.println(strings[0]);
    }

    @Test
    public void demo1oo(){
        List<Integer> list= Arrays.asList(1,2,3,4,5,6);
        //1=<index<3
        list.subList(1,3).forEach(System.out::println);
        List<Integer> list1=JSON.parseArray("[1,2,3,4,5,6]",Integer.TYPE);
        System.out.println(JSON.toJSONString(list1.size()));

        System.out.println(34403049-

            34991453+

            4194748-

            3614724);
    }

}
