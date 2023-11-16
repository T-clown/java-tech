package arithmetic.company.huawei;

import java.util.Scanner;

/**
 * 原理：ip地址的每段可以看成是一个0-255的整数，把每段拆分成一个二进制形式组合起来，然后把这个二进制数转变成
 * 一个长整数。
 * 举例：一个ip地址为10.0.3.193
 * 每段数字             相对应的二进制数
 * 10                   00001010
 * 0                    00000000
 * 3                    00000011
 * 193                  11000001
 * <p>
 * 组合起来即为：00001010 00000000 00000011 11000001,转换为10进制数就是：167773121，即该IP地址转换后的数字就是它了。
 * <p>
 * 数据范围：保证输入的是合法的 IP 序列
 */
public class NumberAndIPConvert {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String ip = in.nextLine();
        long number = Long.parseLong(in.nextLine());

        System.out.println(ip2Number(ip));
        System.out.println(number2Ip(number));
    }

    private static long ip2Number(String ip) {
        String[] split = ip.split("\\.");
        String str = "00000000";
        String binStr = "";
        for (int i = 0; i < split.length; i++) {
            String s = Integer.toBinaryString(Integer.parseInt(split[i]));
            binStr += (str.substring(0, str.length() - s.length()) + s);
        }
        return Long.parseLong(binStr, 2);
    }

    private static String number2Ip(long number) {
        String binStr = Long.toBinaryString(number);
        int firstLength = binStr.length() - 24;
        String first = binStr.substring(0, firstLength);
        String two = binStr.substring(firstLength, firstLength + 8);
        String three = binStr.substring(firstLength + 8, firstLength + 16);
        String four = binStr.substring(firstLength + 16, firstLength + 24);
        return Integer.parseInt(first, 2) + "." + Integer.parseInt(two, 2) + "." + Integer.parseInt(three, 2) + "." + Integer.parseInt(four, 2);
    }

}
