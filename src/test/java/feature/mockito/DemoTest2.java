package feature.mockito;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import feature.entity.Student;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static org.mockito.Mockito.*;

public class DemoTest2 {
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