package arithmetic.top100;

import com.alibaba.fastjson2.JSON;

/**
 * https://mp.weixin.qq.com/s/JzkKSf544W2AGpbqgchNag
 */
public class RotateImage {
    public static void main(String[] args) {
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};

        System.out.println(JSON.toJSONString(rotate2(matrix)));
    }
    public static int[][]  rotate2(int[][] matrix) {
        int[][] rotate = new int[matrix.length][matrix.length];
        for (int j = 0; j < matrix.length; j++) {
            for (int i = matrix.length - 1, z = 0; i >= 0; i--, z++) {
                rotate[j][z] = matrix[i][j];
            }
        }
        return rotate;
    }
    public static void rotate(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        //矩阵的转置
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < i; j++) {
                //第i行只需要处理前i-1个元素
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        /*
        矩阵每一行前后交换
        */
        for (int i = 0; i < row; i++) {
            //第i行只需要处理前col/2个元素
            for (int j = 0; j < col / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][col - 1 - j];
                matrix[i][col - 1 - j] = temp;
            }
        }
    }
}
