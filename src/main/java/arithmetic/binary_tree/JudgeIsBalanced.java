package arithmetic.binary_tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 输入一棵节点数为 n 二叉树，判断该二叉树是否是平衡二叉树。
 * 在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
 * 平衡二叉树（Balanced Binary Tree），具有以下性质：它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
 * 思路：最深叶子节点和最浅叶子节点的距离小于2
 */
public class JudgeIsBalanced {

    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        int depth = 1;
        int minDepth = 0;
        int maxDepth = 0;
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.poll();
                if (node.left == null && node.right == null) {
                    maxDepth = Math.max(maxDepth, depth);
                    if (minDepth == 0) {
                        minDepth = depth;
                    } else {
                        minDepth = Math.min(minDepth, depth);
                    }
                }
                if (node.left != null) {
                    nodeQueue.add(node.left);
                }
                if (node.right != null) {
                    nodeQueue.add(node.right);
                }
            }
            depth++;
        }
        return maxDepth - minDepth < 2;
    }

    public boolean isBalanced(TreeNode root) {
        recursion(root, 1);
        return isBalanced;
    }

    boolean isBalanced = true;

    public int recursion(TreeNode root, int depth) {
        if (root == null) {
            return depth - 1;
        }
        int left = recursion(root.left, depth + 1);
        int right = recursion(root.right, depth + 1);
        isBalanced &= Math.abs(left - right) < 2;
        return Math.max(left, right);
    }


    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(maxDeep(root.left) - maxDeep(root.right)) <= 1;
    }

    private int maxDeep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = 0;
        int right = 0;
        if (root.left != null) {
            left = maxDeep(root.left);
        }
        if (root.right != null) {
            right = maxDeep(root.right);
        }
        return left >= right ? left + 1 : right + 1;
    }
}
