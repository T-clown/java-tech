package arithmetic.binary_tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 求给定二叉树的最大深度，
 * 深度是指树的根节点到任一叶子节点路径上节点的数量。
 * 最大深度是所有叶子节点的深度的最大值。
 * （注：叶子节点是指没有子节点的节点。）
 */
public class MaxDepth {
    public static void main(String[] args) {

    }

    private static int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.poll();
                if (node.left != null) {
                    nodeQueue.add(node.left);
                }
                if (node.right != null) {
                    nodeQueue.add(node.right);
                }
            }
            depth++;
        }
        return depth;
    }


    private static int maxDepth(TreeNode root) {
        return recursiveTraversal(0, root);
    }

    /**
     * 递归遍历
     *
     * @param depth
     * @param node
     */
    private static int recursiveTraversal(int depth, TreeNode node) {
        if (node == null) {
            return depth;
        }
        depth++;
        int left = depth;
        int right = depth;
        if (node.left != null) {
            left = recursiveTraversal(depth, node.left);
        }
        if (node.right != null) {
            right = recursiveTraversal(depth, node.right);
        }
        return Math.max(left, right);
    }

}
