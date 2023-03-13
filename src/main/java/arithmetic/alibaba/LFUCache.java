package arithmetic.alibaba;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 一个缓存结构需要实现如下功能。
 * set(key, value)：将记录(key, value)插入该结构
 * get(key)：返回key对应的value值
 * 但是缓存结构中最多放K条记录，如果新的第K+1条记录要加入，就需要根据策略删掉一条记录，然后才能把新记录加入。
 * 这个策略为：在缓存结构的K条记录中，哪一个key从进入缓存结构的时刻开始，被调用set或者get的次数最少，就删掉这个key的记录；
 * 如果调用次数最少的key有多个，上次调用发生最早的key被删除
 * 这就是LFU缓存替换算法。实现这个结构，K作为参数给出
 */
public class LFUCache {
    /**
     * 节点结构
     */
    static class Node {
        /**
         * 频率
         */
        int freq;
        int key;
        int val;

        public Node(int freq, int key, int val) {
            this.freq = freq;
            this.key = key;
            this.val = val;
        }
    }

    /**
     * 频率到双向链表的哈希表
     */
    private Map<Integer, LinkedList<Node>> freqMap = new HashMap<>();
    /**
     * key到节点的哈希表
     */
    private Map<Integer, Node> cacheMap = new HashMap<>();
    /**
     * 缓存剩余容量
     */
    private int size = 0;
    /**
     * 当前最小频次
     */
    private int minFreq = 0;

    public int[] LFU(int[][] operators, int k) {
        //构建初始化连接
        //链表剩余大小
        this.size = k;
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
     * 调用函数时更新频率或者val值
     *
     * @param node
     * @param key
     * @param value
     */
    private void update(Node node, int key, int value) {
        //找到频率
        int freq = node.freq;
        //原频率中删除该节点
        freqMap.get(freq).remove(node);
        //哈希表中该频率已无节点，直接删除
        if (freqMap.get(freq).isEmpty()) {
            freqMap.remove(freq);
            //若当前频率为最小，最小频率加1
            if (minFreq == freq) {
                minFreq++;
            }
        }
        if (!freqMap.containsKey(freq + 1)) {
            freqMap.put(freq + 1, new LinkedList<>());
        }
        //插入频率加一的双向链表表头，链表中对应：freq key value
        freqMap.get(freq + 1).addFirst(new Node(freq + 1, key, value));
        cacheMap.put(key, freqMap.get(freq + 1).getFirst());
    }

    /**
     * set操作函数
     *
     * @param key
     * @param value
     */
    private void set(int key, int value) {
        //在哈希表中找到key值
        if (cacheMap.containsKey(key)) {
            //若是哈希表中有，则更新值与频率
            update(cacheMap.get(key), key, value);
        } else {
            //哈希表中没有，即链表中没有
            if (size == 0) {
                //满容量取频率最低且最早的删掉
                int oldKey = freqMap.get(minFreq).getLast().key;
                //频率哈希表的链表中删除
                freqMap.get(minFreq).removeLast();
                if (freqMap.get(minFreq).isEmpty()) {
                    freqMap.remove(minFreq);
                }
                //链表哈希表中删除
                cacheMap.remove(oldKey);
            } else {
                //若有空闲则直接加入，容量减1
                size--;
            }
            //最小频率置为1
            minFreq = 1;
            //在频率为1的双向链表表头插入该键
            if (!freqMap.containsKey(1)) {
                freqMap.put(1, new LinkedList<>());
            }
            freqMap.get(1).addFirst(new Node(1, key, value));
            //哈希表key值指向链表中该位置
            cacheMap.put(key, freqMap.get(1).getFirst());
        }
    }

    /**
     * get操作函数
     *
     * @param key
     * @return
     */
    private int get(int key) {
        int res = -1;
        //查找哈希表
        if (cacheMap.containsKey(key)) {
            Node node = cacheMap.get(key);
            //根据哈希表直接获取值
            res = node.val;
            //更新频率
            update(node, key, res);
        }
        return res;
    }
}
