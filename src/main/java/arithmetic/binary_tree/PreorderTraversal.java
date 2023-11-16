package arithmetic.binary_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的前序遍历
 */
public class PreorderTraversal {
    public static void main(String[] args) {
        TreeNode parent = new TreeNode(1, new TreeNode(2, null, null), new TreeNode(3, null, null));
        //Arrays.stream(preorder(parent)).forEach(System.out::println);
        //Arrays.stream(preorder2(parent)).forEach(System.out::println);
        Arrays.stream(preorder3(parent)).forEach(System.out::println);
    }

    /**
     * 非递归方式
     *
     * @param root
     * @return
     */
    private static int[] preorder2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> nodeStack = new Stack<>();
        TreeNode curNode = root;
        while (curNode != null) {
            list.add(curNode.val);
            if (curNode.right != null) {
                nodeStack.push(curNode.right);
            }
            if (curNode.left != null) {
                curNode = curNode.left;
            } else {
                curNode = nodeStack.isEmpty() ? null : nodeStack.pop();
            }
        }
        return list.stream().mapToInt(x -> x).toArray();
    }

    /**
     * 非递归方式
     *
     * @param parent
     * @return
     */
    private static int[] preorder3(TreeNode parent) {
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(parent);
        List<Integer> list = new ArrayList<>();
        while (!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            list.add(curNode.val);
            if (curNode.right != null) {
                nodeStack.push(curNode.right);
            }
            if (curNode.left != null) {
                nodeStack.push(curNode.left);
            }
        }
        return list.stream().mapToInt(x -> x).toArray();
    }


    private static int[] preorder(TreeNode parent) {
        ArrayList<Integer> list = new ArrayList<>();
        recursiveTraversal(list, parent);
        return list.stream().mapToInt(x -> x).toArray();
    }

    /**
     * 递归遍历
     *
     * @param list
     * @param node
     */
    private static void recursiveTraversal(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        if (node.left != null) {
            recursiveTraversal(list, node.left);
        }
        if (node.right != null) {
            recursiveTraversal(list, node.right);
        }
    }
}
