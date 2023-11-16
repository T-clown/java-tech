package arithmetic.company.huawei;

/**
 * 某商店规定：三个空汽水瓶可以换一瓶汽水，允许向老板借空汽水瓶（但是必须要归还）。
 * 小张手上有n个空汽水瓶，她想知道自己最多可以喝到多少瓶汽水。
 * 数据范围：输入的正整数满足
 */
public class ChangeSoda {
    public static void main(String[] args) {
        recursion(3);
        System.out.println( res);
        res=0;

        recursion(10);
        System.out.println( res);
        res=0;

        recursion(81);
        System.out.println( res);
        res=0;

        recursion(0);
        System.out.println( res);
        res=0;

    }


    private static int res;

    private static void recursion(int num) {
        if (num < 2) {
            return;
        }
        if (num == 2) {
            res += 1;
            return;
        }
        int next = num / 3;
        res += next;
        int idle = num % 3;
        recursion(next + idle);
    }
}
