package java8.sort;

import java.util.Random;

public class BubbleSort {
    public static void main(String[] args) {
        int[] a = {12, 20, 5, 16, 15, 1, 30, 45, 23, 9};
        BubbleSort sort = new BubbleSort();
        //int[] a = sort.get();
        long start = System.currentTimeMillis();
        sort.sort(a);
        System.out.println("时间：" + (System.currentTimeMillis() - start));
        for (int i = 0; i < a.length-1; i++) {
            System.out.println(a[i]);
        }
    }

    public void sort(int[] a) {
        for (int i = 0; i < a.length-1; i++) {
            for (int j = i + 1; j < a.length-1; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    public int[] get() {
        int[] a = new int[10000];
        Random r = new Random(10000);
        for (int i = 0; i < a.length-1; i++) {
            a[i] = r.nextInt();
        }
        return a;
    }
}
