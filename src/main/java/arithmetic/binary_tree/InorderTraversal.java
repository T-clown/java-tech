package arithmetic.binary_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的中序遍历
 */
public class InorderTraversal {
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode parent = new TreeNode(1, new TreeNode(2, null, null), new TreeNode(3, null, null));
        TreeNode root = new TreeNode(1, null, new TreeNode(2, new TreeNode(3, null, null), null));
        // Arrays.stream(inorderTraversal(root)).forEach(System.out::println);
        Arrays.stream(inorderTraversal2(root)).forEach(System.out::println);
    }

    private static int[] inorderTraversal3(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        //添加遍历结果的数组
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        //当树节点不为空或栈中有节点时
        while (root != null || !s.isEmpty()) {
            //每次找到最左节点
            while (root != null) {
                s.push(root);
                root = root.left;
            }
            //访问该节点
            TreeNode node = s.pop();
            list.add(node.val);
            //进入右节点
            root = node.right;
        }

        int[] array = new int[list.size()];
        for (int i = 0, size = list.size(); i < size; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * 非递归方式
     *
     * @param root
     * @return
     */
    private static int[] inorderTraversal2(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> nodeStack2 = new Stack<>();
        while (!nodeStack.isEmpty() || !nodeStack2.isEmpty()) {
            TreeNode curNode = nodeStack.empty() ? null : nodeStack.pop();
            if (curNode != null) {
                nodeStack.push(curNode.left);
                nodeStack2.push(curNode);
            } else {
                curNode = nodeStack2.empty() ? null : nodeStack2.pop();
                if (curNode != null) {
                    list.add(curNode.val);
                    if (curNode.right != null) {
                        nodeStack.push(curNode.right);
                    }
                }
            }
        }
        int[] array = new int[list.size()];
        for (int i = 0, size = list.size(); i < size; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    private static int[] inorderTraversal(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        recursion(root);
        int[] array = new int[list.size()];
        for (int i = 0, size = list.size(); i < size; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * 递归遍历
     *
     * @param node
     */
    private static void recursion(TreeNode node) {
        if (node == null) {
            return;
        }
        recursion(node.left);
        list.add(node.val);
        recursion(node.right);
    }
}
