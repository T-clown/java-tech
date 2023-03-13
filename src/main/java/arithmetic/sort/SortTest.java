package arithmetic.sort;

import java.util.Arrays;

public class SortTest {
    private static int[]  arr={1,4,6,2,6,8,8,0,10,9};
    public static void main(String[] args) {
       // bubbleSortTest(arr);
        countSortTest(arr);
        MergeSort2.sort(arr);
    }

    private static void  bubbleSortTest(int[] arr){
        Arrays.stream(BubbleSort.sort(arr)).forEach(x->System.out.print(x+"\t"));
        System.out.println();
        Arrays.stream(BubbleSort.sortT(arr)).forEach(x->System.out.print(x+"\t"));
    }

    private static void  countSortTest(int[] arr){
        Arrays.stream(CountSort.sort(arr)).forEach(x->System.out.print(x+"\t"));

    }

}
