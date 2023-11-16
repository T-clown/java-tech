package arithmetic.double_pointer;

/**
 * 给定一个数组height，长度为n，每个数代表坐标轴中的一个点的高度，height[i]是在第i点的高度，请问，从中选2个高度与x轴组成的容器最多能容纳多少水
 * 1.你不能倾斜容器
 * 2.当n小于2时，视为不能形成容器，请返回0
 * 3.数据保证能容纳最多的水不会超过整形范围，即不会超过Integer.MAX_VALUE
 */
public class MaxArea {
    public static void main(String[] args) {
        int[] arr = {1, 7, 3, 2, 4, 5, 8, 2, 7};
        int[] arr2 = {5, 4, 3, 2, 1, 5};
        System.out.println(maxArea(arr));
        System.out.println(maxArea(arr2));
    }

    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            maxArea = Math.max(area, maxArea);
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return maxArea;
    }

}
