package arithmetic.binary_tree;

import java.util.Arrays;
import java.util.Stack;

/**
 * 重建二叉树
 * 给定节点数为 n 的二叉树的前序遍历和中序遍历结果，请重建出该二叉树并返回它的头结点。
 */
public class RebuildTree {

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 5, 3};
        int[] vin = {4, 2, 5, 1, 3};
        TreeNode root = reConstructBinaryTree(pre, vin);
        System.out.println();
    }

    /**
     * 递归：我的解法
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

    /**
     * 递归：官方解法
     *
     * @param pre
     * @param vin
     * @return
     */
    public TreeNode reConstructBinaryTree2(int[] pre, int[] vin) {
        int n = pre.length;
        int m = vin.length;
        //每个遍历都不能为0
        if (n == 0 || m == 0)
            return null;
        //构建根节点
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < vin.length; i++) {
            //找到中序遍历中的前序第一个元素
            if (pre[0] == vin[i]) {
                //构建左子树
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(vin, 0, i));
                //构建右子树
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(vin, i + 1, vin.length));
                break;
            }
        }
        return root;
    }

    /**
     * 官方解法：栈
     *
     * @param pre
     * @param vin
     * @return
     */
    public TreeNode reConstructBinaryTree3(int[] pre, int[] vin) {
        int n = pre.length;
        int m = vin.length;
        //每个遍历都不能为0
        if (n == 0 || m == 0)
            return null;
        Stack<TreeNode> s = new Stack<TreeNode>();
        //首先建立前序第一个即根节点
        TreeNode root = new TreeNode(pre[0]);
        TreeNode cur = root;
        for (int i = 1, j = 0; i < n; i++) {
            //要么旁边这个是它的左节点
            if (cur.val != vin[j]) {
                cur.left = new TreeNode(pre[i]);
                s.push(cur);
                //要么旁边这个是它的右节点，或者祖先的右节点
                cur = cur.left;
            } else {
                j++;
                //弹出到符合的祖先
                while (!s.isEmpty() && s.peek().val == vin[j]) {
                    cur = s.pop();
                    j++;
                }
                //添加右节点
                cur.right = new TreeNode(pre[i]);
                cur = cur.right;
            }
        }
        return root;
    }


}
