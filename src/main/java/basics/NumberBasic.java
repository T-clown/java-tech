package basics;

import java.math.BigDecimal;

public class NumberBasic {
    public static void main(String[] args) {
        BigDecimal a = BigDecimal.valueOf(0.1);
        System.out.println(a);
        BigDecimal b = new BigDecimal("0.1");
        BigDecimal c = new BigDecimal(0.1);
        System.out.println(c);
        System.out.println(a.compareTo(b));
        System.out.println(a.compareTo(c));
    }
}
