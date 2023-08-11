package arithmetic.binary_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树根节点，请你判断这棵树是不是二叉搜索树。
 * 二叉搜索树满足每个节点的左子树上的所有节点均小于当前节点且右子树上的所有节点均大于当前节点。
 * 思路：中序遍历后，增序即为二叉搜索树
 */
public class JudgeIsValidBST {

    public static void main(String[] args) {
        for (int i = 0; i < list.size() - 1; i++) {
            if(list.get(i)>list.get(i+1)){
                System.out.println(false);
            }
        }
    }

    static List<Integer> list=new ArrayList<>();

    /**
     * 递归
     * @param node
     */
    private static void recursion(TreeNode node) {
        if (node == null) {
            return ;
        }
        recursion(node.left);
        list.add(node.val);
        recursion(node.right);
    }
}
