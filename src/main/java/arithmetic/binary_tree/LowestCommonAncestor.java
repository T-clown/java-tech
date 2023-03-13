package arithmetic.binary_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 1.对于该题的最近的公共祖先定义:对于有根树T的两个节点p、q，最近公共祖先LCA(T,p,q)表示一个节点x，满足x是p和q的祖先且x的深度尽可能大。在这里，一个节点也可以是它自己的祖先.
 * 2.二叉搜索树是若它的左子树不空，则左子树上所有节点的值均小于它的根节点的值； 若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值
 * 3.所有节点的值都是唯一的。
 * 4.p、q 为不同节点且均存在于给定的二叉搜索树中
 */
public class LowestCommonAncestor {


    /**
     * 非递归
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    private static int lowestCommonAncestor2(TreeNode root, int p, int q) {
        List<Integer> path1 = getPath(root, p);
        List<Integer> path2 = getPath(root, q);
        int commonAncestor = 0;
        for (int i = 0; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i).equals(path2.get(i))) {
                commonAncestor = path1.get(i);
            }
        }
        return commonAncestor;
    }

    private static List<Integer> getPath(TreeNode root, int value) {
        List<Integer> path = new ArrayList<>();
        TreeNode curNode = root;
        while (curNode != null) {
            path.add(curNode.val);
            if (curNode.val == value) {
                break;
            }
            if (value < curNode.val || curNode.right == null) {
                curNode = curNode.left;
            } else {
                curNode = curNode.right;
            }
        }
        return path;
    }

    public static int lowestCommonAncestor4(TreeNode root, int p, int q) {
        // write code here
        if (root == null || root.val == p || root.val == q) {
            return root.val;
        }
        if (root.val < p) {
            return lowestCommonAncestor(root.right, p, q);
        }
        if (root.val > q) {
            return lowestCommonAncestor(root.left, p, q);
        }
        return root.val;

    }

    public static int lowestCommonAncestor3(TreeNode root, int p, int q) {
        //空树找不到公共祖先
        if (root == null) {
            return -1;
        }
        //pq在该节点两边说明这就是最近公共祖先
        if ((p >= root.val && q <= root.val) || (p <= root.val && q >= root.val)) {
            return root.val;
        } else if (p <= root.val) {
            //pq都在该节点的左边,进入左子树
            return lowestCommonAncestor3(root.left, p, q);
        } else {
            //pq都在该节点的右边,进入右子树
            return lowestCommonAncestor3(root.right, p, q);
        }
    }

    /**
     * 递归
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    private static int lowestCommonAncestor(TreeNode root, int p, int q) {
        List<Integer> path1 = new ArrayList<>();
        recursivePath(root, path1, p);
        List<Integer> path2 = new ArrayList<>();
        recursivePath(root, path2, q);

        int commonAncestor = 0;
        for (int i = 0; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i).equals(path2.get(i))) {
                commonAncestor = path1.get(i);
            }
        }
        return commonAncestor;
    }


    private static void recursivePath(TreeNode node, List<Integer> path, int value) {
        if (node == null) {
            return;
        }
        path.add(node.val);
        if (node.val == value) {
            return;
        }
        TreeNode left = node.left;
        TreeNode right = node.right;

        if (value < node.val || right == null) {
            recursivePath(left, path, value);
        } else {
            recursivePath(right, path, value);
        }

    }
}
