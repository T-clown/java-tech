package feature.lang3;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;

import org.junit.Test;

public class DateUtilsTest {


    @Test
    public void demo(){
        BigDecimal a = new BigDecimal(10);
        BigDecimal b = new BigDecimal(3);
        BigDecimal divide = a.divide(b, 4, BigDecimal.ROUND_HALF_UP);
        System.out.println(divide);
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMaximumFractionDigits(2);
        System.out.println(numberFormat.format(divide));
        System.out.println(new Date().getTime());
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