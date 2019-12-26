package java8.lang3;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class StringUtilsTest {

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