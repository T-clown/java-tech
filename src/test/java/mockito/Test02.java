package mockito;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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
    public void demo03(){
        doThrow(new RuntimeException()).when(list).get(0);
        doReturn(666).when(list).get(666);
        //doCallRealMethod().when(list).get(0);
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation)  {
                return invocation.getArgument(0);
            }
        }).when(list).get(222);
        doNothing().doThrow(new RuntimeException()).when(list).get(333);
        System.out.println(list.get(666));
        System.out.println(list.get(222));
    }

    @Test
    public void demo04(){
        List list1=new LinkedList();
        List spy=spy(list1);
        //doNothing().when(spy).clear();

        //when(spy.get(0)).thenReturn("two");
        doReturn(3).when(spy).get(0);
        System.out.println(spy.get(0));
        System.out.println(list1.size());
    }

    @Test
    public void demo05(){
        long c=6*24*60*60*1000+1000;
        long a= TimeUnit.MILLISECONDS.toDays(c);
        System.out.println(a);
    }

    @Test
    public void demo06(){
        System.out.println(
            TimeUnit.SECONDS.toHours(60*60));
        ConcurrentHashMap hashMap=new ConcurrentHashMap();
    }

    @Test
    public void demo07(){
        Calendar time = Calendar.getInstance();
        time.setTime(new Date());
        time.add(Calendar.DATE, -30);
        System.out.println(time.getTime().toString());
        System.out.println(DateUtils.addDays(new Date(),-30).toString());
    }
}
