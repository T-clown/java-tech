package arithmetic.exercise;

public class PrimeDemo {
    public static void main(String[] args) {
        System.out.println(getPrimeCount(10));
    }

    /**
     * 统计所有小于非负整数 n 的质数的数量。
     *
     * @param n
     * @return
     */
    private static int getPrimeCount(int n) {
        if (n < 3) {
            return 0;
        }
        if (n == 3) {
            return 1;
        }
        int count = 1;
        boolean isPrime = true;
        for (int i = 3; i < n; i++) {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                count++;
            }
            isPrime = true;
        }
        return count;
    }
}
