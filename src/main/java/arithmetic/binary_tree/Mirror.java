package arithmetic.binary_tree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * 二叉树的遍历
 */
public class Mirror {
    /**
     * 方式三 栈
     *
     * @param pRoot
     * @return
     */
    public static TreeNode mirror3(TreeNode pRoot) {
        //空树
        if (pRoot == null)
            return null;
        //辅助栈
        Stack<TreeNode> s = new Stack<>();
        //根节点先进栈
        s.push(pRoot);
        while (!s.isEmpty()) {
            TreeNode node = s.pop();
            //左右节点入栈
            if (node.left != null) {
                s.push(node.left);
            }
            if (node.right != null) {
                s.push(node.right);
            }
            //交换左右
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
        return pRoot;
    }

    /**
     * 方式二，队列
     *
     * @param pRoot
     * @return
     */
    public static TreeNode mirror(TreeNode pRoot) {
        if (pRoot == null) {
            return null;
        }
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        nodeQueue.add((pRoot));
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.poll();
                TreeNode left = node.left;
                TreeNode right = node.right;

                node.left = right;
                node.right = left;
                if (left != null) {
                    nodeQueue.add(left);
                }
                if (right != null) {
                    nodeQueue.add(right);
                }
            }
        }
        return pRoot;
    }

    /**
     * 方式一，递归
     *
     * @param pRoot
     * @return
     */
    public static TreeNode recursion(TreeNode pRoot) {
        if (pRoot == null) {
            return null;
        }
        TreeNode left = pRoot.left;
        TreeNode right = pRoot.right;

        pRoot.left = right;
        pRoot.right = left;

        recursion(left);
        recursion(right);
        return pRoot;
    }

}
