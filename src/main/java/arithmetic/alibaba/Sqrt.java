package arithmetic.alibaba;

/**
 * 已知sqrt (2)约等于1.414，要求不用数学库，求sqrt (2)精确到小数点后10位
 */
public class Sqrt {

    private static double EPSINON = 0.0000000001;

    public static void main(String[] args) {
        System.out.println(sqrt2());
        System.out.println(Math.sqrt(2));
    }

    private static double sqrt2() {
        double low = 1.4, high = 1.5;
        double mid = (low + high) / 2;
        while (high - low > EPSINON) {
            if (mid * mid > 2) {
                high = mid;
            } else {
                low = mid;
            }
            mid = (high + low) / 2;
        }
        return mid;
    }
}
