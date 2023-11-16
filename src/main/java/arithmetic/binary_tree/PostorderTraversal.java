package arithmetic.binary_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的后序遍历
 */
public class PostorderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(1, null, null), new TreeNode(2, null, null));
        Arrays.stream(postorder3(root)).forEach(System.out::println);
        //Arrays.stream(preorder2(parent)).forEach(System.out::println);
        //Arrays.stream(preorder3(parent)).forEach(System.out::println);
    }

    /**
     * 非递归方式
     *
     * @param root
     * @return
     */
    private static int[] postorder2(TreeNode root) {
        //添加遍历结果的数组
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        TreeNode pre = null;
        while (root != null || !s.isEmpty()) {
            //每次先找到最左边的节点
            while (root != null) {
                s.push(root);
                root = root.left;
            }
            //弹出栈顶
            TreeNode node = s.pop();
            //如果该元素的右边没有或是已经访问过
            if (node.right == null || node.right == pre) {
                //访问中间的节点
                list.add(node.val);
                //且记录为访问过了
                pre = node;
            } else {
                //该节点入栈
                s.push(node);
                //先访问右边
                root = node.right;
            }
        }
        //返回的结果
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
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
    private static int[] postorder3(TreeNode root) {
        //结果栈
        Stack<TreeNode> nodeStack = new Stack<>();
        //左节点栈
        Stack<TreeNode> nodeStack2 = new Stack<>();
        while (root != null) {
            //入栈
            nodeStack.push(root);
            if (root.right != null) {
                if (root.left != null) {
                    nodeStack2.push(root.left);
                }
                root = root.right;
            } else {
                if (root.left != null) {
                    root = root.left;
                } else {
                    //当前节点的左右节点都为空的时候，则把父节点弹出
                    root = nodeStack2.isEmpty() ? null : nodeStack2.pop();
                }
            }
        }
        int[] array = new int[nodeStack.size()];
        for (int i = 0, size = nodeStack.size(); i < size; i++) {
            array[i] = nodeStack.pop().val;
        }
        return array;
    }


    private static int[] postorder(TreeNode parent) {
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
        if (node.left != null) {
            recursiveTraversal(list, node.left);
        }
        if (node.right != null) {
            recursiveTraversal(list, node.right);
        }
        list.add(node.val);
    }
}
