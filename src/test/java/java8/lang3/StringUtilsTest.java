package java8.lang3;

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

}
