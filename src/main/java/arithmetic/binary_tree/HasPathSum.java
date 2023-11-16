package arithmetic.binary_tree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 给定一个二叉树root和一个值 sum ，判断是否有从根节点到叶子节点的节点值之和等于 sum 的路径。
 * 1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
 * 2.叶子节点是指没有子节点的节点
 * 3.路径只能从父节点到子节点，不能从子节点到父节点
 * 4.总节点数目为n
 */
public class HasPathSum {
    public static void main(String[] args) {

    }

    private static boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        Stack<Integer> sumStack = new Stack<>();
        sumStack.push(root.val);
        while (!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            int value = sumStack.pop();
            if (curNode.left == null && curNode.right == null && sum == value) {
                return true;
            }
            if (curNode.left != null) {
                nodeStack.push(curNode.left);
                sumStack.push(curNode.left.val + value);
            }
            if (curNode.right != null) {
                nodeStack.push(curNode.right);
                sumStack.push(curNode.right.val + value);
            }
        }
        return false;
    }


    private static boolean hasPathSum(TreeNode root, int value) {
        return recursiveTraversal(0, value, root);
    }

    /**
     * 递归遍历
     *
     * @param sum
     * @param node
     */
    private static boolean recursiveTraversal(int sum, int value, TreeNode node) {
        if (node == null) {
            return value == sum;
        }
        sum += node.val;
        if (node.left == null && node.right == null) {
            return value == sum;
        }
        boolean leftEqual = false;
        boolean rightEqual = false;
        if (node.left != null) {
            leftEqual = recursiveTraversal(sum, value, node.left);
        }
        if (node.right != null) {
            rightEqual = recursiveTraversal(sum, value, node.right);
        }
        return leftEqual || rightEqual;
    }

}
