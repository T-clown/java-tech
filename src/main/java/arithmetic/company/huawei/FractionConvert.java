package arithmetic.company.huawei;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 将真分数分解为埃及分数
 * 分子为1的分数称为埃及分数。现输入一个真分数(分子比分母小的分数，叫做真分数)，请将该分数分解为埃及分数。如：8/11 = 1/2+1/5+1/55+1/110。
 * 注：真分数指分子小于分母的分数，分子和分母有可能gcd不为1！
 * 如有多个解，请输出任意一个。
 * <p>
 * 输入描述：
 * 输入一个真分数，String型
 * <p>
 * 输出描述：
 * 输出分解后的string
 */
public class FractionConvert {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] split = line.split("/");
        int c = Integer.parseInt(split[0]);
        int p = Integer.parseInt(split[1]);

        System.out.println(JSON.toJSONString(decomposeFraction(c, p)));
    }


    public static List<String> decomposeFraction(int numerator, int denominator) {
        List<String> egyptianFractions = new ArrayList<>();

        if (numerator == 0 || denominator == 0) {
            throw new IllegalArgumentException("Invalid fraction: division by zero");
        }

        if (numerator % denominator == 0) {
            egyptianFractions.add(Integer.toString(numerator / denominator));
            return egyptianFractions;
        }

        if (denominator % numerator == 0) {
            int factor = denominator / numerator;
            for (int i = 0; i < factor; i++) {
                egyptianFractions.add("1/" + factor);
            }
            return egyptianFractions;
        }

        while (numerator != 1) {
            int nextDenominator = (denominator + numerator - 1) / numerator;
            egyptianFractions.add("1/" + nextDenominator);
            numerator = numerator * nextDenominator - denominator;
            denominator = denominator * nextDenominator;
        }

        egyptianFractions.add("1/" + denominator);
        return egyptianFractions;
    }
}
