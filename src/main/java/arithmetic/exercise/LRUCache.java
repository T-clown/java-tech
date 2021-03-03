package arithmetic.exercise;

import java.util.HashMap;
import java.util.LinkedList;

import lombok.Data;

/**
 * Least Recently Used
 */
public class LRUCache {
    /**
     * 头节点
     */
    private Node first = null;
    /**
     * 尾节点
     */
    private Node last = null;
    /**
     * key 映射到 Node(key, val)
     */
    private HashMap<Integer, Node> nodeMap = new HashMap<>();

    /**
     * 容量
     */
    public int capacity;

    public int get(int key) {
        if (nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);
            remove(node);
            addFirst(node);
        }
        return -1;
    }

    public int put(int key, int value) {
        Node node = new Node(key, value,null,null);
        if (nodeMap.containsKey(key)) {
            remove(nodeMap.get(key));
        } else {

        }
        return 0;
    }

    // 在链表头部添加节点 x，时间 O(1)
    public void addFirst(Node x) {
        Node f = first;
        if (f == null) {
            first = x;
        } else {

        }
        nodeMap.put(x.key, x);
    }

    // 删除链表中的 x 节点（x ⼀定存在）
    // 由于是双链表且给的是⽬标 Node 节点，时间 O(1)
    public void remove(Node x) {
        //头节点
        if (x.prev == null) {
            first = x.next;
            x.next.prev = null;
        }
        //尾节点
        if (x.next == null) {
            last = x.prev;
            x.prev.next = null;
        }

        nodeMap.remove(x.getKey());
    }

    // 删除链表中最后⼀个节点，并返回该节点，时间 O(1)
    public Node removeLast() {
        if (last == null) {
            return null;
        }
        Node oldLast = last;
        Node newLast = last.prev;
        newLast.next = null;
        last = newLast;
        nodeMap.remove(last.key);
        return oldLast;
    }

    // 返回链表⻓度，时间 O(1)
    public int size() {
        return nodeMap.size();
    }

    @Data
    class Node {
        public int key;
        public int val;
        public Node next;
        public Node prev;

        public Node(int k, int v, Node prev, Node next) {
            this.key = k;
            this.val = v;
            this.prev = prev;
            this.next = next;
        }
    }
}
