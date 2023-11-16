package entity;

import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import patterns.proxy.jdk.proxy.MapUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class TreeNode {
    String value;
    List<TreeNode> children;

    public TreeNode() {
        children = new ArrayList<>();
    }

    public TreeNode(String value) {
        this.value = value;
        children = new ArrayList<>();
    }

    @Override
    public String toString() {
        return value;
    }

    public static void main(String[] args) {
        TreeNode root = buildTree();
        List<List<TreeNode>> lists = bfsTree(root);
        List<List<TreeNode>> list2 = dfsTree(root);
        List<TreeNode> treeNodes = dfsGetTreeLeaf(root);
        Map<String, String> leafPathMap = getLeafPathMap(Collections.singletonList(root));
    }

    public static TreeNode buildTree() {
        // 建立一棵树
        TreeNode root = new TreeNode("A");
        // 第二层
        root.children.add(new TreeNode("B"));
        root.children.add(new TreeNode("C"));
        // 第三层
        root.children.get(0).children.add(new TreeNode("D"));
        root.children.get(0).children.add(new TreeNode("E"));
        root.children.get(1).children.add(new TreeNode("F"));
        root.children.get(1).children.add(new TreeNode("H"));
        root.children.get(1).children.add(new TreeNode("G"));
        // 第四层
        root.children.get(0).children.get(1).children.add(new TreeNode("I"));
        return root;
    }

    /**
     * 深度优先遍历（递归方式） --- 树（Tree）
     */
    public static List<List<String>> recurTree(TreeNode root) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        path.add(root.value);
        findPath(result, root, path);
        return result;
    }

    private static void findPath(List<List<String>> result, TreeNode node, List<String> path) {
        if (node.children == null || node.children.size() <= 0) {
            result.add(path);
            return;
        }

        for (int i = 0; i < node.children.size(); i++) {
            TreeNode child = node.children.get(i);
            List<String> cPath = new ArrayList<>();
            cPath.addAll(path);
            cPath.add(child.value);
            findPath(result, child, cPath);
        }
    }

    /**
     * 深度优先遍历（非递归方式） ----- 查找树的全部叶子路径
     *
     * @param root 根节点
     * @return 叶子路径的集合
     */
    public static List<List<TreeNode>> dfsTree(TreeNode root) {
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<List<TreeNode>> pathStack = new Stack<>();
        Stack<String> pathStack2 = new Stack<>();
        Map<String, String> pathMap = Maps.newHashMap();
        List<List<TreeNode>> result = new ArrayList<>();
        nodeStack.push(root);
        ArrayList<TreeNode> arrayList = new ArrayList<>();
        arrayList.add(root);
        pathStack.push(arrayList);
        pathStack2.push(root.value);

        while (!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            List<TreeNode> curPath = pathStack.pop();
            String pop = pathStack2.pop();
            if (curNode.children == null || curNode.children.size() <= 0) {
                result.add(curPath);
                pathMap.put(curNode.value, pop);
            } else {
                int childSize = curNode.children.size();
                for (int i = childSize - 1; i >= 0; i--) {
                    TreeNode node = curNode.children.get(i);
                    nodeStack.push(node);
                    List<TreeNode> list = new ArrayList<>(curPath);
                    list.add(node);
                    pathStack.push(list);
                    pathStack2.push(pop + node.value);
                }
            }
        }
        return result;
    }

    public static List<TreeNode> dfsGetTreeLeaf(TreeNode root) {
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<List<TreeNode>> pathStack = new Stack<>();
        List<TreeNode> result = new ArrayList<>();
        nodeStack.push(root);
        ArrayList<TreeNode> arrayList = new ArrayList<>();
        arrayList.add(root);
        pathStack.push(arrayList);

        while (!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            if (curNode.children == null || curNode.children.size() <= 0) {
                result.add(curNode);
            } else {
                int childSize = curNode.children.size();
                for (int i = childSize - 1; i >= 0; i--) {
                    TreeNode node = curNode.children.get(i);
                    nodeStack.push(node);
                }
            }
        }
        return result;
    }

    public static Map<String, String> getLeafPathMap(List<TreeNode> roots) {
        if (CollectionUtils.isEmpty(roots)) {
            return Maps.newHashMap();
        }
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<String> leafPathStack = new Stack<>();
        Map<String, String> leafPathMap = new HashMap<>();
        //1.把根节点压栈
        roots.forEach(x -> {
            nodeStack.push(x);
            leafPathStack.push(x.value);
        });

        while (!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            String curPath = leafPathStack.pop();
            if (CollectionUtils.isEmpty(curNode.children)) {
                leafPathMap.put(curNode.value, curPath);
            } else {
                int childSize = curNode.children.size();
                for (int i = childSize - 1; i >= 0; i--) {
                    TreeNode node = curNode.children.get(i);
                    nodeStack.push(node);
                    leafPathStack.push(curPath + "-" + node.value);
                }
            }
        }
        return leafPathMap;
    }

    /**
     * 广度优先遍历 ---- 查找树的全部叶子路径
     *
     * @param root 根节点
     * @return 叶子路径的集合
     */
    public static List<List<TreeNode>> bfsTree(TreeNode root) {
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<List<TreeNode>> qstr = new LinkedList<>();
        List<List<TreeNode>> result = new ArrayList<>();
        nodeQueue.add(root);
        ArrayList<TreeNode> arrayList = new ArrayList<>();
        qstr.add(arrayList);

        while (!nodeQueue.isEmpty()) {
            TreeNode curNode = nodeQueue.remove();
            List<TreeNode> curList = qstr.remove();

            if (curNode.children == null || curNode.children.size() <= 0) {
                curList.add(curNode);
                result.add(curList);
            } else {
                for (int i = 0; i < curNode.children.size(); i++) {
                    TreeNode treeNode = curNode.children.get(i);
                    nodeQueue.add(treeNode);
                    List<TreeNode> list = new ArrayList<>(curList);
                    list.add(curNode);
                    qstr.add(list);
                }
            }
        }

        return result;
    }

    public static void recurTree2(TreeNode root) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        path.add(root.value);
        findPath(result, root, path, "E");
        System.out.println(result);
    }

    private static void findPath(List<List<String>> result, TreeNode node, List<String> path, String target) {
        if (target.equals(node.value)) {
            result.add(path);
            return;
        }

        if (node.children == null || node.children.size() <= 0) {
            return;
        }

        for (int i = 0; i < node.children.size(); i++) {
            TreeNode child = node.children.get(i);
            List<String> cPath = new ArrayList<>();
            cPath.addAll(path);
            cPath.add(child.value);
            if (result.size() > 0)
                break;
            findPath(result, child, cPath, target);
        }
    }


}

