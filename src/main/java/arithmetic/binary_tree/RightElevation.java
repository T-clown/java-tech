package arithmetic.binary_tree;

import com.alibaba.fastjson.JSON;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * 请根据二叉树的前序遍历，中序遍历恢复二叉树，并打印出二叉树的右视图
 */
public class RightElevation {

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 5, 3};
        int[] vin = {4, 2, 5, 1, 3};
        //1.重建二叉树
        TreeNode root = reConstructBinaryTree(pre, vin);
        //2.层序遍历，取每层最后一个节点
        //int[] ints = rightElevation(root);
        //System.out.println(JSON.toJSONString(ints));
    }


    /**
     * 深度优先搜索函数
     * 右视图
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> rightSideView(TreeNode root) {
        //右边最深处的值
        Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
        //记录最大深度
        int max_depth = -1;
        //维护深度访问节点
        Stack<TreeNode> nodes = new Stack<TreeNode>();
        //维护dfs时的深度
        Stack<Integer> depths = new Stack<Integer>();
        nodes.push(root);
        depths.push(0);
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.pop();
            int depth = depths.pop();
            if (node != null) {
                //维护二叉树的最大深度
                max_depth = Math.max(max_depth, depth);
                //如果不存在对应深度的节点我们才插入
                if (mp.get(depth) == null) {
                    mp.put(depth, node.val);
                }
                nodes.push(node.left);
                nodes.push(node.right);
                depths.push(depth + 1);
                depths.push(depth + 1);
            }
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        //结果加入链表
        for (int i = 0; i <= max_depth; i++) {
            res.add(mp.get(i));
        }
        return res;
    }

    /**
     * 层序遍历取最后一个节点
     *
     * @param treeNode
     * @return
     */
    public static int[] rightElevation(TreeNode treeNode) {
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        nodeQueue.add(treeNode);
        List<Integer> res = new ArrayList<>();
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = nodeQueue.remove();
                if (poll.left != null) {
                    nodeQueue.add(poll.left);
                }
                if (poll.right != null) {
                    nodeQueue.add(poll.right);
                }
                if (i == (size - 1)) {
                    res.add(poll.val);
                }
            }
        }
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    /**
     * 递归建树
     *
     * @param pre
     * @param vin
     * @return
     */
    public static TreeNode reConstructBinaryTree(int[] pre, int[] vin) {
        if (pre == null || pre.length == 0) {
            return null;
        }
        TreeNode node = new TreeNode(pre[0]);
        if (pre.length == 1) {
            return node;
        }
        int midIdx = 0;
        for (int i = 0; i < vin.length; i++) {
            if (node.val == vin[i]) {
                //找出父节点中序遍历的索引位置
                midIdx = i;
                break;
            }
        }
        //切分
        int[] leftVin = new int[0];
        int[] leftPre = new int[0];
        if (midIdx > 0) {
            leftVin = Arrays.copyOfRange(vin, 0, midIdx);
            leftPre = Arrays.copyOfRange(pre, 1, leftVin.length + 1);
        }

        int[] rightVin = new int[0];
        int[] rightPre = new int[0];

        if (midIdx < vin.length - 1) {
            rightVin = Arrays.copyOfRange(vin, midIdx + 1, vin.length);
            rightPre = Arrays.copyOfRange(pre, leftVin.length + 1, vin.length);
        }
        node.left = reConstructBinaryTree(leftPre, leftVin);
        node.right = reConstructBinaryTree(rightPre, rightVin);
        return node;
    }
}
