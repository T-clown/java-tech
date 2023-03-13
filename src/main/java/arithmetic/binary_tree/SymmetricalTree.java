package arithmetic.binary_tree;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一棵二叉树，判断其是否是自身的镜像（即：是否对称）
 */
public class SymmetricalTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, new TreeNode(2, null, null));
        TreeNode root2 = new TreeNode(1, new TreeNode(2, null, null), null);
        TreeNode root3 = new TreeNode(1, new TreeNode(2, null, null), new TreeNode(2, null, null));
        System.out.println(JSON.toJSONString(isSymmetrical(root2)));
        System.out.println(JSON.toJSONString(isSymmetrical(root3)));
    }

    /**
     * 非递归方式
     *
     * @param pRoot
     * @return
     */
    private static boolean isSymmetrical2(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        //左队列
        Queue<TreeNode> leftQueue = new LinkedList<>();
        leftQueue.add(pRoot.left);
        //右队列
        Queue<TreeNode> rightQueue = new LinkedList<>();
        rightQueue.add(pRoot.right);
        while (!leftQueue.isEmpty()) {
            int size = leftQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode left = leftQueue.poll();
                TreeNode right = rightQueue.poll();
                if (left == null && right == null) {
                    continue;
                }
                if ((left == null || right == null) || left.val != right.val) {
                    return false;
                }
                leftQueue.add(left.left);
                leftQueue.add(left.right);

                rightQueue.add(right.right);
                rightQueue.add(right.left);
            }
        }
        return true;
    }


    private static boolean isSymmetrical(TreeNode root) {
        if (root == null) {
            return true;
        }
        return recursive(root.left, root.right);
    }

    /**
     * 递归方式
     *
     * @param left
     * @param right
     * @return
     */
    private static boolean recursive(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if ((left == null || right == null) || left.val != right.val) {
            return false;
        }
        return recursive(left.right, right.left) && recursive(left.left, right.right);
    }


}
