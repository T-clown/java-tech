package arithmetic.binary_tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 已知两颗二叉树，将它们合并成一颗二叉树。合并规则是：都存在的结点，就将结点值加起来，
 * 否则空的位置就由另一个树的结点来代替。
 */
public class MergeTree {


    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        Queue<TreeNode> q1 = new LinkedList<>();
        q1.add(t1);
        Queue<TreeNode> q2 = new LinkedList<>();
        q2.add(t2);
        TreeNode lastNode1 = t1;
        TreeNode lastNode2 = t2;
        while (!q1.isEmpty()) {
            int size = q1.size();
            for (int i = 0; i < size; i++) {
                TreeNode node1 = q1.poll();
                TreeNode node2 = q2.poll();
                if (node2 == null) {
                    continue;
                }
                if (node1 == null) {

                }
                if (node1.left == null) {
                    node1.left = node2.left;
                }
                if (i % 2 == 0) {
                    //左

                }
                if (i % 2 == 1) {
                    //右
                }
            }
        }
        return t1;
    }



    public TreeNode mergeTrees2 (TreeNode t1, TreeNode t2) {
        //若只有一个节点返回另一个，两个都为null自然返回null
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        //合并根节点
        TreeNode head = new TreeNode(t1.val + t2.val);
        //连接后的树的层次遍历节点
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        //分别存两棵树的层次遍历节点
        Queue<TreeNode> q1 = new LinkedList<TreeNode>();
        Queue<TreeNode> q2 = new LinkedList<TreeNode>();
        q.offer(head);
        q1.offer(t1);
        q2.offer(t2);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode node = q.poll();
            TreeNode node1 = q1.poll();
            TreeNode node2 = q2.poll();
            TreeNode left1 = node1.left;
            TreeNode left2 = node2.left;
            TreeNode right1 = node1.right;
            TreeNode right2 = node2.right;
            if(left1 != null || left2 != null){
                //两个左节点都存在
                if(left1 != null && left2 != null){
                    TreeNode left = new TreeNode(left1.val + left2.val);
                    node.left = left;
                    //新节点入队列
                    q.offer(left);
                    q1.offer(left1);
                    q2.offer(left2);
                    //只连接一个节点
                }else if(left1 != null)
                    node.left = left1;
                else
                    node.left = left2;
            }
            if(right1 != null || right2 != null){
                //两个右节点都存在
                if(right1 != null && right2 != null) {
                    TreeNode right = new TreeNode(right1.val + right2.val);
                    node.right = right;
                    //新节点入队列
                    q.offer(right);
                    q1.offer(right1);
                    q2.offer(right2);
                    //只连接一个节点
                }else if(right1 != null)
                    node.right = right1;
                else
                    node.right = right2;
            }
        }
        return head;
    }
    /**
     * 递归
     *
     * @param t1
     * @param t2
     * @return
     */
    public static TreeNode recursive(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val += t2.val;
        t1.left = recursive(t1.left, t2.left);
        t1.right = recursive(t1.right, t2.right);
        return t1;
    }
}
