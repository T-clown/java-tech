package arithmetic.array;

/**
 * 在一个二维数组array中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 解法一：全部遍历，时间复杂度O(MN)
 * 解法二：每个使用二分查找，时间复杂度O(NlogN)
 */
public class FindValue {
    public static void main(String[] args) {
        int[][] arr = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        int[][] arr2 = {{1, 1}};
        System.out.println(find(1, arr));
    }

    private static boolean find(int target, int[][] array) {
        if (array == null) {
            return false;
        }
        int arrayColLen = array.length;
        int arrayRowLen;
        if (arrayColLen == 0 || (arrayRowLen = array[0].length) == 0) {
            return false;
        }
        if (array[0][0] > target || array[arrayColLen - 1][arrayRowLen - 1] < target) {
            return false;
        }
        int row = 0;
        int col = arrayColLen - 1;
        while (row < arrayRowLen && col >= 0) {
            int value = array[col][row];
            if (value > target) {
                col--;
            } else if (value < target) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }
}
