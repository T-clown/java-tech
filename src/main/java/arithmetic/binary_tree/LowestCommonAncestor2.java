package arithmetic.binary_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一棵二叉树(保证非空)以及这棵树上的两个节点对应的val值 o1 和 o2，请找到 o1 和 o2 的最近公共祖先节点。
 */
public class LowestCommonAncestor2 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, null, null), new TreeNode(3, null, null));
        System.out.println(lowestCommonAncestor(root, 2, 3));
    }

    public static int lowestCommonAncestor(TreeNode root, int o1, int o2) {
        List<Integer> path1 = new ArrayList<>();
        dfsPath(root, path1, o1);
        find = false;
        List<Integer> path2 = new ArrayList<>();
        dfsPath(root, path2, o2);
        int value = -1;
        for (int i = 0; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i).equals(path2.get(i))) {
                value = path1.get(i);
            }
        }
        return value;
    }

    static boolean find = false;

    private static void dfsPath(TreeNode node, List<Integer> path, int value) {
        if (node == null || find) {
            return;
        }
        path.add(node.val);
        if (node.val == value) {
            find = true;
            return;
        }
        dfsPath(node.left, new ArrayList<>(path), value);
        dfsPath(node.right, new ArrayList<>(path), value);
        if (find) {
            return;
        }
        path.remove(path.size() - 1);
    }

    public static int lowestCommonAncestor2(TreeNode root, int o1, int o2) {
        //该子树没找到，返回-1
        if (root == null) {
            return -1;
        }
        //该节点是其中某一个节点
        if (root.val == o1 || root.val == o2) {
            return root.val;
        }
        //左子树寻找公共祖先
        int left = lowestCommonAncestor(root.left, o1, o2);
        //右子树寻找公共祖先
        int right = lowestCommonAncestor(root.right, o1, o2);
        //左子树为没找到，则在右子树中
        if (left == -1) {
            return right;
        }
        //右子树没找到，则在左子树中
        if (right == -1) {
            return left;
        }
        //否则是当前节点
        return root.val;
    }

}
