package arithmetic.company.alibaba;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 设计LRU(最近最少使用)缓存结构，该结构在构造时确定大小，假设大小为 capacity ，操作次数是 n ，并有如下功能:
 * 1. Solution(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * 2. get(key)：如果关键字 key 存在于缓存中，则返回key对应的value值，否则返回 -1 。
 * 3. set(key, value)：将记录(key, value)插入该结构，如果关键字 key 已经存在，则变更其数据值 value，如果不存在，则向缓存中插入该组 key-value ，如果key-value的数量超过capacity，弹出最久未使用的key-value
 * <p>
 * 提示:
 * 1.某个key的set或get操作一旦发生，则认为这个key的记录成了最常使用的，然后都会刷新缓存。
 * 2.当缓存的大小超过capacity时，移除最不经常使用的记录。
 * 3.返回的value都以字符串形式表达，如果是set，则会输出"null"来表示(不需要用户返回，系统会自动输出)，方便观察
 * 4.函数set和get必须以O(1)的方式运行
 * 5.为了方便区分缓存里key与value，下面说明的缓存里key用""号包裹
 * <p>
 * 哈希表+双向链表
 */
public class LRUCache {
    /**
     * 双向链表结构
     */
    static class Node {
        int key;
        int val;
        Node pre;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.pre = null;
            this.next = null;
        }
    }

    /**
     * 哈希表
     */
    private final Map<Integer, Node> cacheMap = new HashMap<>();
    /**
     * 头节点
     */
    private final Node head = new Node(-1, -1);
    /**
     * 尾结点
     */
    private final Node tail = new Node(-1, -1);

    private int size = 0;

    public int[] LRU(int[][] operators, int k) {
        //构建初始化连接
        //链表剩余大小
        this.size = k;
        this.head.next = this.tail;
        this.tail.pre = this.head;
        //获取操作数
        int len = (int) Arrays.stream(operators).filter(x -> x[0] == 2).count();
        int[] res = new int[len];
        //遍历所有操作
        for (int i = 0, j = 0; i < operators.length; i++) {
            if (operators[i][0] == 1) {
                //set操作
                set(operators[i][1], operators[i][2]);
            } else {
                //get操作
                res[j++] = get(operators[i][1]);
            }
        }
        return res;
    }

    /**
     * 插入函数
     *
     * @param key
     * @param val
     */
    private void set(int key, int val) {
        //没有见过这个key，新值加入
        if (!cacheMap.containsKey(key)) {
            Node node = new Node(key, val);
            cacheMap.put(key, node);
            if (size <= 0) {
                //超出大小，移除最后一个
                removeLast();
            } else {
                //大小还有剩余
                //大小减1
                size--;
            }
            //加到链表头
            insertFirst(node);
        } else {
            //哈希表中已经有了，即链表里也已经有了
            cacheMap.get(key).val = val;
            //访问过后，移到表头
            moveToHead(cacheMap.get(key));
        }
    }

    /**
     * 获取数据函数
     *
     * @param key
     * @return
     */
    private int get(int key) {
        int res = -1;
        if (cacheMap.containsKey(key)) {
            Node node = cacheMap.get(key);
            res = node.val;
            moveToHead(node);
        }
        return res;
    }

    /**
     * 移到表头函数
     *
     * @param node
     */
    private void moveToHead(Node node) {
        //已经到了表头
        if (node.pre == head) {
            return;
        }
        //将节点断开，取出来
        node.pre.next = node.next;
        node.next.pre = node.pre;
        //插入第一个前面
        insertFirst(node);
    }

    /**
     * 将节点插入表头函数
     *
     * @param node
     */
    private void insertFirst(Node node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    /**
     * 删去表尾函数，最近最少使用
     */
    private void removeLast() {
        //哈希表去掉key
        cacheMap.remove(tail.pre.key);
        //断连该节点
        tail.pre.pre.next = tail;
        tail.pre = tail.pre.pre;
    }
}
