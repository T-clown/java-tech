package arithmetic.binary_tree;

import java.util.Stack;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表
 * <p>
 * 这个其实跟二叉树的中序遍历一样
 */
public class ConvertToLinkedList {

    public static void main(String[] args) {

    }


    private static TreeNode convert2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode dumpy = new TreeNode(-1, null, null);
        //添加遍历结果的数组
        Stack<TreeNode> s = new Stack<>();
        TreeNode curNode = dumpy;
        //当树节点不为空或栈中有节点时
        while (root != null || !s.isEmpty()) {
            //每次找到最左节点
            while (root != null) {
                s.push(root);
                root = root.left;
            }
            //访问该节点
            TreeNode node = s.pop();
            //连接
            curNode.right = node;
            node.left = curNode;

            curNode = node;
            //进入右节点
            root = node.right;
        }
        TreeNode res = dumpy.right;
        res.left = null;
        return res;
    }

    /**
     * 递归
     *
     * @param pRootOfTree
     * @return
     */
    public static TreeNode convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        TreeNode dumpy = new TreeNode(-1);
        recursion(dumpy, pRootOfTree);
        TreeNode res = dumpy.right;
        res.left = null;
        return res;
    }


    public static TreeNode recursion(TreeNode head, TreeNode node) {
        if (node == null) {
            return head;
        }
        TreeNode left = node.left;

        if (left != null) {
            head = recursion(head, left);
        }

        head.right = node;
        node.left = head;

        head = node;

        TreeNode right = node.right;
        if (right != null) {
            head = recursion(head, right);
        }
        return head;
    }

    TreeNode pre = null;

    public TreeNode convert3(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        convert3(pRootOfTree.right);
        if (pre != null) {
            pRootOfTree.right = pre;
            pre.left = pRootOfTree;
        }
        pre = pRootOfTree;
        convert3(pRootOfTree.left);
        return pre;
    }
}
