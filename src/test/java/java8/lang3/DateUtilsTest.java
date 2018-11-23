package java8.lang3;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

public class DateUtilsTest {

    @Test
    public void dateTest(){
        Date date=new Date();
        System.out.println(date.toString());
        date= DateUtils.addDays(date,-30);
        System.out.println(date.toString());
    }


}
