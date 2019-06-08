package java8.lang3;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateUtilsTest {

    @Test
    public void dateTest(){
        Date date=new Date();
        System.out.println(date.toString());
        date= DateUtils.addDays(date,-30);
        System.out.println(date.toString());

        System.out.println("什么");

    }

    @Test
    public void demo(){
        BigDecimal a = new BigDecimal(10);
        BigDecimal b = new BigDecimal(3);
        BigDecimal divide = a.divide(b, 4, BigDecimal.ROUND_HALF_UP);
        System.out.println(divide);
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMaximumFractionDigits(2);
        divide.doubleValue();
        Object q=1;
        System.out.println(String.valueOf(q));
        System.out.println(numberFormat.format(divide));

        List<Integer> list=new ArrayList<>();

    }

    @Test
    public void demo1(){
        int n=10;
        int count=1;
        int a=0;
        boolean isPrimes=true;
        for(int i=3;i<n;i++){
            for(int j=2;j<i;j++){
                ++a;
                if(i%j==0){
                    isPrimes=false;
                    break;
                }
            }
            if(isPrimes) count++;
            isPrimes=true;
        }
        System.out.println(count);
        System.out.println(a);
    }
}