package java8.lang3;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

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
aaaaaaaaaaaaaaaaaaaaaa
package java8.lang3;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class StringUtilsTest {
    @Test
    public void strEmpty(){
        String str="    ";
        System.out.println(StringUtils.isBlank(str));
        System.out.println(StringUtils.isEmpty(str));
        System.out.println(str.toCharArray().length);
    }

    @Test
    public void isEmptyTest() {
        /**
         * isNotEmpty=!isEmpty
         */
        StringUtils.isEmpty(null);//true;
        StringUtils.isEmpty(""); // true;
        StringUtils.isEmpty(" "); // false
        StringUtils.isEmpty("        "); // false
        StringUtils.isEmpty("aa");//false
        StringUtils.isEmpty(" aaa ");//false
    }

    @Test
    public void isBlankTest() {
        /**
         * isNotBlank=!isBlank
         */
        System.out.println(StringUtils.isBlank(null));//true
        System.out.println(StringUtils.isBlank(""));//true
        System.out.println(StringUtils.isBlank(" "));//true
        System.out.println(StringUtils.isBlank("   "));//true
        System.out.println(StringUtils.isBlank("\n\t"));//true
        System.out.println(StringUtils.isBlank("aaa"));//false
        System.out.println(StringUtils.isBlank(" aa "));//false
    }

    @Test
    public void demo() {
        String state = "Virginia";
        /**
         * StringUtils.isNumeric( testString ) :如果testString全由数字组成返回True
         */
        System.out.println("Is state number? " + StringUtils.isNumeric(
            state));
        /**
         * StringUtils.isAlpha( testString ) :如果testString全由字母组成返回True
         */
        System.out.println("Is state alpha? " + StringUtils.isAlpha(state)
        );
        /**
         * StringUtils.isAlphanumeric( testString ) :如果testString全由数字或数字组成返回True
         */
        System.out.println("Is state alphanumeric? " + StringUtils.isAlphanumeric(state));
        /**
         * StringUtils.isAlphaspace( testString )  :如果testString全由字母或空格组成返回True
         */
        System.out.println("Is state alphaspace? " + StringUtils.isAlphaSpace(state));
    }


    @Test
    public void t(){
        Random random=new Random();
        random.ints().limit(5).forEach(System.out::println);
    }

}
