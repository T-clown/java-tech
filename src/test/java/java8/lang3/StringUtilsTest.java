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

    /**
     直接使用双引号声明出来的 String 对象会直接存储在常量池中。
     如果不是用双引号声明的 String 对象，可以使用 String 提供的 intern 方String.intern() 是一个 Native 方法，
     它的作用是：如果运行时常量池中已经包含一个等于此 String 对象内容的字符串，则返回常量池中该字符串的引用；
     如果没有，则在常量池中创建与此 String 内容相同的字符串，并返回常量池中创建的字符串的引用。
     */
    @Test
    public void strTest(){
        String s1 = new String("计算机");
        String s2 = s1.intern();
        String s3 = "计算机";
        System.out.println(s2);//计算机
        System.out.println(s1 == s2);//false，因为一个是堆内存中的String对象一个是常量池中的String对象，
        System.out.println(s3 == s2);//true，因为两个都是常量池中的String对
    }

    /**
     * 字符串拼接
     */
    @Test
    public void strConcat(){
        String str1 = "str";
        String str2 = "ing";

        String str3 = "str" + "ing";//常量池中的对象
        String str4 = str1 + str2; //在堆上创建的新的对象
        String str5 = "string";//常量池中的对象
        System.out.println(str3 == str4);//false
        System.out.println(str3 == str5);//true
        System.out.println(str4 == str5);//false
    }


    @Test
    public void t(){
        Random random=new Random();
        random.ints().limit(5).forEach(System.out::println);
    }

}
