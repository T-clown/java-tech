package arithmetic.exercise;

/**
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。
 * n<=39
 */
public class FibonacciSequence {


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
       if (n <= 0)
           return 0;
       if (n == 1||n==2)
           return 1;
       return  recursion(n-2)+recursion(n-1);
   }


}




package arithmetic.exercise;


public class FrogJumpStep {
    public static void main(String[] args) {
        System.out.println(getN(10));
    }

    /**
     * /**
     *  *一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     *  f(n)=f(n-2)+f(n-1)
     *
     * 台阶数      跳跃方式
     *   1          1      1
     *   2          2      1+1   2+0
     *   3          3      1+1+1   1+2   2+1
     *   4          5      1+1+1+1(1种)   1+2+1  1+1+2  2+1+1  2+2
     *   5          8      全是1(1种)   两个2(3种)  3个1(4种)
     */

    int jumpFloor(int number) {
        if (number <= 0) {
            return 0;
        }
        if (number == 1) {
            return 1;
        }
        if (number == 2) {
            return 2;
        }
        int first = 1, second = 2, third = 0;
        for (int i = 3; i <= number; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }

    /**
     *一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     * f(n)=f(n-1)+f(n-2)+...+f(1)
     * f(n-1)=f(n-2)+...+f(1)
     * f(n)=2f(n-1)
     */
    private static int getN(int n ){
        return 1<<--n;
    }

}



package arithmetic.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.alibaba.fastjson.JSON;

import arithmetic.sort.BubbleSort;
import com.google.common.primitives.Ints;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 */
public class Intersection {

    public static void main(String[] args) {
        int[] A={1,23,4,56,7,88,10,123,88,6};
        int[] B={11,88,88,6,2};
        int[] com= solutionThree(BubbleSort.sort(B),BubbleSort.sort(A));
        System.out.println(JSON.toJSONString(com));
    }

    private static int[] get(int[] nums1,int[] nums2){
        List<Integer> a= IntStream.of(nums1.length>=nums2.length?nums2:nums1).boxed().sorted().collect(Collectors.toList());
        List<Integer> b= IntStream.of(nums1.length<=nums2.length?nums2:nums1).boxed().sorted().collect(Collectors.toList());
        List<Integer> indexs=new ArrayList<>();
        List<Integer> result=new ArrayList<>();
        for(int i=0;i<a.size();i++){
            for(int j=0;j<b.size();j++){
                if(a.get(i)==b.get(j)&&!indexs.contains(j)){
                    result.add(a.get(i));
                    indexs.add(j);
                    break;
                }
            }
        }
        int[] num=new int[result.size()];
        for(int i=0;i<result.size();i++){
            num[i]=result.get(i);
        }
        return num;
    }

    /**
     * 方法二：对其中一个数组排序，采用二分查找法
     * 需要记录索引
     * @param A
     * @param B
     * @return
     */
    private static int[] solutionTwo(int[] A,int[] B){
        return null;
    }
    /**
     * 方法三：对两个数组排序，采用顺序查找法
     * @param A
     * @param B
     * @return
     */
    private static int[] solutionThree(int[] A,int[] B){
        int index=0;
        //A<B
        List<Integer> result=new ArrayList<>();
       for (int i=0;i<A.length;i++){
           int target=A[i];
           for (int j=index;j<B.length;j++){
                if(target==B[j]){
                    //添加
                    result.add(target);
                    break;
                }else if(target<B[j]){
                    index=j-1;
                    break;
                }
           }
       }
       return  Ints.toArray(result);
    }

    /**
     * 方法一：顺序查找
     * @param A
     * @param B
     * @return
     */
    private static int[] solutionOne(int[] A,int[] B){
       int[] com=new int[0];
       int index=0;
        List<Integer>  indexs=new ArrayList<>();
        for (int i=0;i<A.length;i++){
            for (int j=0;j<B.length;j++){
                if(A[i]==B[j]&& !indexs.contains(j)){
                   com[index]=A[i];
                   index++;
                    indexs.add(j);
                }
            }
        }
        return com
            ;
    }
}



package arithmetic.exercise;

import java.util.Arrays;

public class MaxNum {
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


package arithmetic.exercise;

public class Prime {
    public static void main(String[] args) {
        System.out.println(getPrimeCount(10));
    }

    /**
     * 统计所有小于非负整数 n 的质数的数量。
     * @param n
     * @return
     */
    private static  int getPrimeCount(int n){
        if(n<3) return 0;
        if(n==3) return 1;
        int count=1;
        boolean isPrime=true;
        for(int i=3;i<n;i++){
            for(int j=2;j<i;j++){
                if(i%j==0){
                    isPrime=false;
                    break;
                }
            }
            if(isPrime) count++;
            isPrime=true;
        }
        return count;
    }
}




package arithmetic.exercise;

/**
 * 本参考程序来自九章算法，由 @九章算法 提供。版权所有，转发请注明出处。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，算法强化班，Java入门与基础算法班，Android 项目实战班，
 * - Big Data 项目实战班，算法面试高频题班, 动态规划专题班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/?source=code
 */

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

/**
 * 以字符串的形式给定两个非负整数 num1 和 num2，返回 num1 和 num2 的乘积。
 */

public class Solution {
    public static void main(String[] args) {
        String a = "abcde";
        String b = "bcda1e";
        System.out.println(rotateString(a, b));
        System.out.println(a.contains(b));
    }

    private static String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }

        int len1 = num1.length(), len2 = num2.length();
        int len3 = len1 + len2;
        int i, j, product, carry;

        int[] num3 = new int[len3];
        for (i = len1 - 1; i >= 0; i--) {
            carry = 0;
            for (j = len2 - 1; j >= 0; j--) {
                product = carry + num3[i + j + 1] +
                    Character.getNumericValue(num1.charAt(i)) *
                        Character.getNumericValue(num2.charAt(j));
                num3[i + j + 1] = product % 10;
                carry = product / 10;
            }
            num3[i + j + 1] = carry;
        }

        StringBuilder sb = new StringBuilder();
        i = 0;

        while (i < len3 - 1 && num3[i] == 0) {
            i++;
        }

        while (i < len3) {
            sb.append(num3[i++]);
        }

        return sb.toString();
    }

    private static boolean rotateString(String A, String B) {
        if (StringUtils.isEmpty(A) || StringUtils.isEmpty(B) || A.length() != B.length()) {
            return false;
        }
        char[] aChars = A.toCharArray();
        char[] bChars = B.toCharArray();
        Arrays.sort(aChars);
        Arrays.sort(bChars);
        if (!Arrays.toString(aChars).equals(Arrays.toString(bChars))) {
            return false;
        }
        for (int i = 0; i < A.length(); i++) {
            String a = A.substring(0, i + 1);
            String b = A.substring(i + 1, A.length());
            if (B.contains(a) && B.contains(b)) {
                return true;
            }
        }
        return false;
    }
}

















