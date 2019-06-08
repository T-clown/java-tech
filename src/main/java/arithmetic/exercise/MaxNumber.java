package arithmetic.exercise;

import java.util.Arrays;

public class MaxNumber {
    public static void main(String[] args) {
        //System.out.println( getMaxNum(9973));;
        int[] arr=new int[]{5,0,3,8,6};
        System.out.println(splitArray(arr));;
        // System.out.println(jumpLast(arr));
    }

    /**
     * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
     */
    private static int getMaxNum(int num){
        char[] chars= String.valueOf(num).toCharArray();
        char[] sorChars= Arrays.copyOf(chars,chars.length);
        Arrays.sort(sorChars);
        for(int i=0 ;i<chars.length;i++){
            if(chars[i]!=sorChars[chars.length-i-1]){
                for(int j=chars.length-1;j>=0;j--){
                    if(chars[j]==sorChars[chars.length-i-1]){
                        char temp=chars[i];
                        chars[i]=chars[j];
                        chars[j]=temp;
                    }
                }
                break;
            }
        }
        return Integer.parseInt(String.valueOf(chars));
    }

    /**
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个位置。
     */
    private static boolean jumpLast(int[] arr){
        for(int i=arr.length-2;i>=0;i--){
            if(arr[i]==0){
                boolean flag=false;
                for(int j=0;j<i;j++){
                    if(arr[j]+j>i){
                        flag=true;
                    }
                }
                if(!flag) return false;
            }
        }
        return true;


    }

    /**
     * 给定一个数组 A，将其划分为两个不相交（没有公共元素）的连续子数组 left 和 right， 使得：
     *
     *     left 中的每个元素都小于或等于 right 中的每个元素。
     *     left 和 right 都是非空的。
     *     left 要尽可能小。
     *
     * 在完成这样的分组后返回 left 的长度。可以保证存在这样的划分方法。
     */
    private static int splitArray(int[] arr){
        int leftMax=arr[0];
        int index=0;
        int max=0;
        for (int i=0;i<arr.length;i++){
            if(max<arr[i]){
                max=arr[i];
            }
            if(leftMax>arr[i]){
                leftMax=max;
                index=i;
            }
        }
        return index+1;
    }

    /**
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     *
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     */
    private static boolean exists(char[][] board, String word){
        char[] wordChars=word.toCharArray();
        int index=0;
        int m,n;
        char[] existChars=new char[word.length()];
        for(int i=0;i<board.length;i++){
            char[] chars=board[i];
            for(int j=0;j<chars.length;j++){
                if(chars[j]==wordChars[index]){
                    existChars[index]=wordChars[index];
                    m=i;n=j;
                }
            }
        }
        return false;
    }
}
