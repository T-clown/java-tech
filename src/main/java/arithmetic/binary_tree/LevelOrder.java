package arithmetic.binary_tree;

import com.alibaba.fastjson.JSON;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

/**
 * 给定一个二叉树，返回该二叉树层序遍历的结果，（从左到右，一层一层地遍历）
 */
public class LevelOrder {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, new TreeNode(2, null, null));
        TreeNode root2 = new TreeNode(1, new TreeNode(2, null, null), null);
        TreeNode root3 = new TreeNode(1, new TreeNode(2, null, null), new TreeNode(3, null, null));
        System.out.println(JSON.toJSONString(levelOrder4(root3)));
        System.out.println(JSON.toJSONString(levelOrder2(root3)));
    }

    public static ArrayList<ArrayList<Integer>> levelOrder4(TreeNode root) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(root);
        while (!treeNodes.isEmpty()) {
            ArrayList<Integer> levelList = new ArrayList<>();
            int n = treeNodes.size();
            for (int i = 0; i < n; i++) {
                TreeNode cur = treeNodes.get(i);
                levelList.add(cur.val);
                if (cur.left != null) {
                    treeNodes.add(cur.left);
                }
                if (cur.right != null) {
                    treeNodes.add(cur.right);
                }
            }
            ArrayList<TreeNode> newTreeNodes = new ArrayList<>();
            for (int i = n; i < treeNodes.size(); i++) {
                newTreeNodes.add(treeNodes.get(i));
            }
            treeNodes = newTreeNodes;
            list.add(levelList);
        }
        return list;
    }

    public ArrayList<ArrayList<Integer>> levelOrder3(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        //如果是空，则直接返回空数组
        if (root == null) {
            return res;
        }
        //队列存储，进行层次遍历
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            //记录二叉树的某一行
            ArrayList<Integer> row = new ArrayList<>();
            int n = q.size();
            //因先进入的是根节点，故每层节点多少，队列大小就是多少
            for (int i = 0; i < n; i++) {
                TreeNode cur = q.poll();
                row.add(cur.val);
                //若是左右孩子存在，则存入左右孩子作为下一个层次
                if (cur.left != null) {
                    q.add(cur.left);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                }
            }
            //每一层加入输出
            res.add(row);
        }
        return res;
    }

    private static ArrayList<ArrayList<Integer>> levelOrder2(TreeNode root) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        recursiveTraversal(list, 1, root);
        return list;
    }

    /**
     * 递归遍历
     *
     * @param list
     * @param node
     */
    private static void recursiveTraversal(ArrayList<ArrayList<Integer>> list, int level, TreeNode node) {
        if (node == null) {
            return;
        }
        ArrayList<Integer> levelList = list.size() < level ? new ArrayList<>() : list.remove(level - 1);
        levelList.add(node.val);
        list.add(level - 1, levelList);
        if (node.left != null) {
            recursiveTraversal(list, level + 1, node.left);
        }
        if (node.right != null) {
            recursiveTraversal(list, level + 1, node.right);
        }
    }

}
