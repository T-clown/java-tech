package arithmetic.company.huawei;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 迷宫问题
 * 定义一个二维数组 N*M ，如 5 × 5 数组下所示：
 * <p>
 * int maze[5][5] = {
 * 0, 1, 0, 0, 0,
 * 0, 1, 1, 1, 0,
 * 0, 0, 0, 0, 0,
 * 0, 1, 1, 1, 0,
 * 0, 0, 0, 1, 0,
 * };
 * <p>
 * 它表示一个迷宫，其中的1表示墙壁，0表示可以走的路，只能横着走或竖着走，不能斜着走，要求编程序找出从左上角到右下角的路线。入口点为[0,0],既第一格是可以走的路。
 */
//todo
public class Labyrinth {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int m = Integer.parseInt(in.nextLine());
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] s = in.nextLine().split(" ");
            for (int j = 0; j < s.length; j++) {
                arr[i][j] = Integer.parseInt(s[j]);
            }
        }
        int x = 0;
        int y = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int v = arr[i][j];
                if (v == 1) {

                }
            }
        }

    }

    //搜索所有可能的路径
    public static ArrayList<int[]> path = new ArrayList<>();
    //最短路径
    public static ArrayList<int[]> best_path = new ArrayList<>();

    public static void dfs(int i, int j, int[][] maze) {
        //越界了
        if (i < 0 || i > maze.length - 1 || j < 0 || j > maze[0].length - 1) {
            return;
        }
        //撞墙了
        if (maze[i][j] == 1) {
            return;
        }
        //终止条件,找到终点了
        if (i == maze.length - 1 && j == maze[0].length - 1) {
            //添加终点
            path.add(new int[]{maze.length - 1, maze[0].length - 1});
            //遇到更短的路径
            if (best_path.size() == 0 || path.size() < best_path.size()) {
                //清空之前的路径
                best_path.clear();
                for (int[] path0 : path) {
                    best_path.add(path0);
                }
            }
            return;
        }
        //标记走过的点
        maze[i][j] = 1;
        //添加到路径中
        path.add(new int[]{i, j});
        //以i j为中心，上下左右搜索
        dfs(i - 1, j, maze);
        dfs(i + 1, j, maze);
        dfs(i, j - 1, maze);
        dfs(i, j + 1, maze);
        //回溯，恢复到之前的状态
        maze[i][j] = 0;
        //回溯，移除最后一个点
        path.remove(path.size() - 1);
    }
}
