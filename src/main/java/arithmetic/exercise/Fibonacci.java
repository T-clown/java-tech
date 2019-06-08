package arithmetic.exercise;

/**
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。
 * n<=39
 */
public class Fibonacci {


    public static void main(String[] args) {
        long a=System.currentTimeMillis();
        System.out.println(getN(1000000000));
        long b=System.currentTimeMillis();
        System.out.println(fibonacci(1000000000));
        long c=System.currentTimeMillis();
        System.out.println(recursion(40));
        long d=System.currentTimeMillis();

        System.out.println("时间1："+(b-a)+"\t"+"时间2："+(c-b)+"\t"+"时间3："+(d-c));
    }

    /**
     * 迭代法
     * @param n
     * @return
     */
    private  static int  getN(int n ){
        if(n<=0)
            return 0;
        if(n < 3)
            return 1;
        int M=0,N=0;
        for (int i=0;i<n;i++){
            if(i<3){
                M=N=1;
            }
            N+=M;
            M=N-M;
        }
        return  N;
    }
    private  static int fibonacci(int number) {
        if (number <= 0) {
            return 0;
        }
        if (number == 1 || number == 2) {
            return 1;
        }
        int first = 1, second = 1, third = 0;
        for (int i = 3; i <= number; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }

    private static int recursion(int n){
        if (n <= 0) {
            return 0;
        }
        if (n == 1||n==2) {
            return 1;
        }
        return  recursion(n-2)+recursion(n-1);
    }


}