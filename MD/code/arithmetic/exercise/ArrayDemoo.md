package arithmetic.exercise;

import java.util.Arrays;

public class ArrayDemo {
    public static void main(String[] args) {
       // int[] arrays = new int[] {3, 2, 2, 1};
        int[] arrays = new int[] {3, 2, 2, 1};
        int k = 4;
        arrays=getArray(arrays,k);
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] >= k) {
                System.out.println("分界位子："+i);
                break;
            }
        }
        Arrays.stream(arrays).forEach(System.out::println);
    }

    private static int[] getArray(int[] arrs, int k) {
        for (int i = 0; i < arrs.length; i++) {
            for (int j = i + 1; j < arrs.length; j++) {
                if (arrs[i] >= k && arrs[j] <= k) {
                    int tmp = arrs[j];
                    arrs[j] = arrs[i];
                    arrs[i] = tmp;
                }
            }
        }
        return arrs;
    }

}
