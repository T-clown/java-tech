package arithmetic.search;

import arithmetic.sort.common.BubbleSort;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 二分查找
 */
public class BinSearch {

    public static void main(String[] args) {
        int[] arr={1,23,4,56,7,88,10,123,88,6};
        int[] arr2={11,88,88,6};
        int[] sortArr= BubbleSort.sort(arr);
        IntStream.of(arr).boxed().sorted().collect(Collectors.toList());
        System.out.println(binarySearch(sortArr,4));
    }

    public static int binarySearch(int[] A, int target, int n){
        int low = 0, high = n, mid;
        while(low <= high){
            mid = low + (high - low) / 2;
            if(A[mid] == target){
                return mid;
            }else if(A[mid] > target){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return -1;
    }

    //递归
    public static int binarySearch(int[] Arr,int key,int low,int high){


        while(low<=high){
            int mid=(low+high)/2;
            if(key==Arr[mid]){
                return mid;
            }
            else if(key<Arr[mid]){
                return binarySearch(Arr,key,low,mid-1);
            }
            else{
                return binarySearch(Arr,key,mid+1,high);
            }
        }
        return -1;
    }

    public static int binarySearch(int[] Arr,int key){
        int low=0;
        int high=Arr.length-1;

        while(low<=high){
            int mid=(low+high)/2;
            if(key==Arr[mid]){
                return mid;
            }
            else if(key<Arr[mid]){
                high=mid-1;
            }
            else{
                low=mid+1;
            }
        }
        return -1;
    }


}