package arithmetic.company.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 求一个数的立方根
 */
public class CubeRoot {
    public static void main(String[] args) throws IOException {
        double value = 19.9;
        System.out.println(demo(value));
        System.out.println(dichotomy(value));
        System.out.println(getCubeRoot(value));
    }

    private static double demo(double num) {
        double x = 1.0;
        for (; Math.abs(Math.pow(x, 3) - num) > 1e-3;
             x = x - ((Math.pow(x, 3) - num) / (3 * Math.pow(x, 2)))) {
        }
        return x;
    }


    /**
     * 使用类似二分的思路
     *
     * @param num
     * @return
     */
    public static double dichotomy(double num) {
        double right, left, mid = 0.0;
        //一定要注意边界条件，输入的num可能是负数  将x<-1的边界范围定为[x,1]，x>1的边界范围定为[-1,x]
        right = Math.max(1.0, num);
        left = Math.min(-1.0, num);
        while (right - left > 0.0001) {
            mid = (left + right) / 2;
            //如果乘积大于num，说明立方根在mid的左侧
            if (mid * mid * mid > num) {
                right = mid;
            }
            //如果乘积小于num，说明立方根在mid的右侧
            else if (mid * mid * mid < num) {
                left = mid;
            } else {
                return mid;
            }
        }
        return right;
    }

    private static double getCubeRoot(double num) {
        //判断输入是否为0 为0直接返回
        if (num == 0) {
            return 0;
        }
        double x0 = num;
        double x1 = (2 * x0 + num / (x0 * x0)) / 3;
        //迭代结束的条件
        while (Math.abs(x1 - x0) > 0.0001) {
            x0 = x1;
            //更新x1
            x1 = (2 * x0 + num / (x0 * x0)) / 3;
        }
        //最终结果
        return x1;
    }

}
